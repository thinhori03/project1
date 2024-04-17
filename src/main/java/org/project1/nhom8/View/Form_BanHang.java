/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.project1.nhom8.View;

import org.project1.nhom8.Store;
import org.project1.nhom8.dto.AVViewModel;
import org.project1.nhom8.dto.Cart;
import org.project1.nhom8.dto.CartDetail;
import org.project1.nhom8.dto.CartDetailViewModel;
import org.project1.nhom8.dto.CartViewModel;
import org.project1.nhom8.dto.StoreProductViewModel;
import org.project1.nhom8.dto.provider.AVViewModelProvider;
import org.project1.nhom8.dto.provider.CartDetailViewModelProvider;
import org.project1.nhom8.dto.provider.CartViewModelProvider;
import org.project1.nhom8.dto.provider.StoreProductViewModelProvider;
import org.project1.nhom8.exception.CustomerPhoneNumberExistedException;
import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.KhachHangModel;
import org.project1.nhom8.model.KhuyenMai;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.KhachHangConnection;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.VoucherRepository;
import org.project1.nhom8.service.CartService;
import org.project1.nhom8.service.HoaDonService;
import org.project1.nhom8.service.LoginService;
import org.project1.nhom8.service.StoreProductService;
import org.project1.nhom8.util.CartUtil;
import org.project1.nhom8.util.InvoiceCreateUpdateState;
import org.project1.nhom8.util.TrangThaiHoaDon;
import org.project1.nhom8.util.VietNamPattern;
import org.project1.nhom8.util.data.convert.DefaultConverter;
import org.project1.nhom8.util.swing.GeneralComboBoxModel;
import org.project1.nhom8.util.swing.GeneralDocumentListener;
import org.project1.nhom8.util.swing.GeneralTableModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PC
 */
public class Form_BanHang extends javax.swing.JPanel {
    private final StoreProductService storeProductService;

    private final StoreProductViewModelProvider storeProductProvider;

    private final GeneralTableModel<StoreProductViewModel> tabletableModel;

    private final GeneralTableModel<CartDetailViewModel> cartDetailGeneralTableModel;

    private final CartDetailViewModelProvider cartDetailViewModelProvider;
    private final CartViewModelProvider cartViewModelProvider;

    private final GeneralTableModel<CartViewModel> cartTableModel;

    private final KhachHangConnection khachHangConnection;

    private final GiaRepository giaRepository;

    private final SPCTRepository spctRepository;

    private final CartService cartService;

    private final HoaDonService hoaDonService;

    public final VoucherRepository voucherRepository;

    private final GeneralComboBoxModel<AVViewModel> aVComboModel;

    private final AVViewModelProvider avViewModelProvider;

    private Cart cart;

    public Form_BanHang() {
        initComponents();

        this.storeProductService = new StoreProductService();

        this.tabletableModel = new GeneralTableModel<>(StoreProductViewModel.class);

        this.cartDetailViewModelProvider = new CartDetailViewModelProvider();

        this.cartViewModelProvider = new CartViewModelProvider();

        this.cartTableModel = new GeneralTableModel<>(CartViewModel.class);

        this.cartDetailGeneralTableModel = new GeneralTableModel<>(CartDetailViewModel.class);

        this.storeProductProvider = new StoreProductViewModelProvider();

        this.giaRepository = new GiaRepository();

        this.spctRepository = new SPCTRepository();

        this.hoaDonService = new HoaDonService();

        this.khachHangConnection = new KhachHangConnection();

        this.voucherRepository = new VoucherRepository();

        this.aVComboModel = new GeneralComboBoxModel<>(AVViewModel.class);

        this.avViewModelProvider = new AVViewModelProvider();

        cartService = Store.getCartService();

        customerName.getDocument().addDocumentListener(new GeneralDocumentListener() {
            @Override
            public void onChange() {
                createInvoice.setEnabled(customerName.getText().trim().matches(VietNamPattern.TEN.getValue()));
            }
        });

        customerPhoneNumber.getDocument().addDocumentListener(new GeneralDocumentListener() {
            @Override
            public void onChange() {
                createInvoice.setEnabled(customerPhoneNumber.getText().trim().matches("^0([1-9]+){9}$") || customerPhoneNumber.getText().isEmpty());
            }
        });

        buy.getDocument().addDocumentListener(new GeneralDocumentListener() {
            @Override
            public void onChange() {

                Double price = null;

                if (cart == null) {
                    return;
                }

                try {

                    Double paymentPrice = Double.parseDouble(buy.getText());
                    Double changePrice = paymentPrice - CartUtil.getFinalPrice(cart);

                    if (changePrice < 0) {
                        payment.setEnabled(false);
                    } else {

                        change.setText(changePrice + "");

                        cart.setPayment(paymentPrice);
                        payment.setEnabled(true);
                    }

                } catch (Exception e) {
                    payment.setEnabled(false);
                }
            }
        });

        findByProductName.getDocument().addDocumentListener(new GeneralDocumentListener() {
            @Override
            public void onChange() {
                loadProductView();
            }
        });

        fillCart();
    }

    public void loadProductView() {
        this.storeProductService.fetch(findByProductName.getText().trim());
        storeProductService.refreshAll(this.cartService.getCartsAsList());
        this.productView.setModel(tabletableModel
                .toTableModel(storeProductProvider.getModel(storeProductService.getStoreProductAsList())));
    }

    public void loadInvoice() {

        cartService.refresh();

        this.invoiceView.setModel(cartTableModel.toTableModel(cartViewModelProvider
                .getModel(cartService.getCartsAsList())));
    }

    public void loadCartDetail(Cart cart) {
        List<CartDetailViewModel> cartDetailView = null;

        if (cart == null) {
            cartDetailView = new ArrayList<>();
        } else {
            cartDetailView = cartDetailViewModelProvider
                    .getModel(cart.getProducts().values().stream().toList());
        }

        cartView.setModel(cartDetailGeneralTableModel.toTableModel(cartDetailView));
    }

    public void fillCart() {
        if (this.cart != null) {

            createInvoice.setText(InvoiceCreateUpdateState.UPDATE.getValue());

            getVoucher();

            invoiceId.setText(this.cart.getInvoiceId());
            customerName.setText(this.cart.getCustomerName());
            customerPhoneNumber.setText(this.cart.getCustomerPhoneNumber());
            creationDate.setText(DefaultConverter.VietnameseDateFormat(this.cart.getCreationDate()));

            totalPrice.setText(CartUtil.getTotalPrice(this.cart) + "");
            finalPrice.setText(CartUtil.getFinalPrice(this.cart) + "");

            VoucherModel voucher = voucherRepository.findById(this.cart.getVoucherId());
            if (voucher != null) {
                applyVoucher.setText(voucher.getGiaTri() + "");
            }

            List<VoucherModel> availableVoucher = voucherRepository.getAvailableVoucher(CartUtil
                    .getTotalPrice(this.cart));

            // not empty
            if (!availableVoucher.isEmpty()) {
                try {
                    this.availableVoucher.setModel(aVComboModel.toComboBoxModel(avViewModelProvider
                            .getModel(availableVoucher), ""));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            createInvoice.setText(InvoiceCreateUpdateState.CREATE.getValue());
            clear();
        }

        loadProductView();
        loadInvoice();
        loadCartDetail(this.cart);
    }

    public void getVoucher() {
        VoucherModel voucher = voucherRepository.getVoucherToApply(CartUtil
                .getTotalPrice(this.cart));

        if (voucher != null) {
            this.cart.setVoucherId(voucher.getMaVoucher());
        }
    }

    public void clear() {
        customerName.setText("");
        customerPhoneNumber.setText("");
        invoiceId.setText("");
        creationDate.setText("");
        finalPrice.setText("");
        totalPrice.setText("");
        applyVoucher.setText("");
        buy.setText("");
        change.setText("");
        createInvoice.setEnabled(false);
        payment.setEnabled(false);

        this.cart = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        invoiceView = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        cartView = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        productView = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        findByProductName = new javax.swing.JTextField();
        refreshProducts = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        customerName = new javax.swing.JTextField();
        invoiceId = new javax.swing.JTextField();
        creationDate = new javax.swing.JTextField();
        totalPrice = new javax.swing.JTextField();
        paymentMethod = new javax.swing.JComboBox<>();
        applyVoucher = new javax.swing.JTextField();
        createInvoice = new javax.swing.JButton();
        payment = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        customerPhoneNumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cancelInvoice = new javax.swing.JButton();
        availableVoucher = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        finalPrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        buy = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        change = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(3000, 3000));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chờ"));

        invoiceView.setAutoCreateRowSorter(true);
        invoiceView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        invoiceView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoiceViewMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(invoiceView);

        jTabbedPane1.addTab("hóa đơn", jScrollPane2);

        cartView.setAutoCreateRowSorter(true);
        cartView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        cartView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartViewMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(cartView);

        jTabbedPane1.addTab("giỏ hàng", jScrollPane5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        productView.setAutoCreateRowSorter(true);
        productView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Size", "Màu sắc", "Số lượng", "Giá bán"
            }
        ));
        productView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productViewMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(productView);

        jLabel1.setText("Tìm kiếm sản phẩm:");

        refreshProducts.setText("Làm mới");
        refreshProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshProductsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(findByProductName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshProducts)
                        .addGap(18, 18, 18))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findByProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshProducts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Đơn hàng"));

        jLabel3.setText("Tên KH:");

        jLabel4.setText("Mã HĐ:");

        jLabel5.setText("Ngày tạo:");

        jLabel6.setText("Tổng tiền:");

        jLabel7.setText("voucher");

        jLabel9.setText("Hình thức thanh toán:");

        invoiceId.setEditable(false);

        creationDate.setEditable(false);

        totalPrice.setEditable(false);

        paymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));

        applyVoucher.setEditable(false);

        createInvoice.setText("Tạo hóa đơn");
        createInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createInvoiceActionPerformed(evt);
            }
        });

        payment.setText("thanh toán");
        payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentActionPerformed(evt);
            }
        });

        refresh.setText("Làm mới");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jLabel2.setText("SĐT");

        cancelInvoice.setText("hủy hóa đơn");
        cancelInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelInvoiceActionPerformed(evt);
            }
        });

        jLabel8.setText(" thành tiền");

        finalPrice.setEditable(false);

        jLabel10.setText("tiền khách đưa");

        jLabel11.setText("tiền thừa");

        change.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(applyVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(invoiceId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(customerName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(creationDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customerPhoneNumber, javax.swing.GroupLayout.Alignment.TRAILING)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createInvoice))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(payment))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(availableVoucher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finalPrice)
                    .addComponent(buy)
                    .addComponent(change))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(invoiceId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(creationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(availableVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(applyVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(finalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(buy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createInvoice)
                    .addComponent(refresh))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(paymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payment)
                    .addComponent(cancelInvoice))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 707, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed

        clear();

    }//GEN-LAST:event_refreshActionPerformed

    private void createInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createInvoiceActionPerformed

        // update customer info
        if (this.cart != null) {
            this.cart.setCustomerName(customerName.getText().trim());
            this.cart.setCustomerPhoneNumber(customerPhoneNumber.getText().trim());
            return;
        }

        Cart cart = new Cart();

        cart.setCustomerName(customerName.getText().trim());
        cart.setCustomerPhoneNumber(customerPhoneNumber.getText().trim());

        cart.setCreationDate(new Date());
        cart.setCreator(LoginService.lg);

        try {
            cartService.add(cart);
        } catch (CustomerPhoneNumberExistedException e) {
            JOptionPane.showMessageDialog(this,
                    "hóa đơn của khách hàng có số địen thoại: " + e.getMessage() + " đã tồn tại.");
        }
        loadInvoice();
    }//GEN-LAST:event_createInvoiceActionPerformed

    private void invoiceViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoiceViewMouseClicked

        if (invoiceView.getSelectedRow() < 0) {
            return;
        }

        Integer invoiceIndex = invoiceView.getSelectedRow();

        String invoiceId = invoiceView.getValueAt(invoiceIndex, 0)
                .toString();

        this.cart = cartService.getCarts().get(invoiceId);

        fillCart();
        loadInvoice();
        loadCartDetail(this.cart);
        loadProductView();

    }//GEN-LAST:event_invoiceViewMouseClicked

    private void cartViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartViewMouseClicked

        if (this.cart == null) {
            return;
        }

        if (evt.getClickCount() == 2) {
            int quantity = 0;
            int productIndex = cartView.getSelectedRow();

            if (productIndex < 0) {
                return;
            }

            Integer productId = Integer.parseInt(cartView
                    .getValueAt(productIndex, 0).toString());

            CartDetail cartDetail = this.cart.getProducts().get(productId);

            try {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("nhập số lượng"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "giá trị không hợp lệ");
                return;
            }

            if (quantity > cartDetail.getQuantity()) {
                JOptionPane.showMessageDialog(this, "số lượng quá lớn");
                return;
            }

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "số lượng không hợp lệ");
                return;
            }

            storeProductService.refreshRemove(this.cart.getProducts().get(productId), quantity);

            if (quantity == cartDetail.getQuantity()) {
                this.cart.getProducts().remove(productId);
            } else if (quantity < cartDetail.getQuantity()) {
                cartDetail.setQuantity(cartDetail.getQuantity() - quantity);
            }

            totalPrice.setText(CartUtil.getTotalPrice(this.cart) + "");
            fillCart();
        }

    }//GEN-LAST:event_cartViewMouseClicked

    private void productViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productViewMouseClicked

        if (this.cart == null) {
            return;
        }

        if (evt.getClickCount() == 2) {

            int quantity = 0;

            try {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("nhập số lượng"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "giá trị không hợp lệ");
                return;
            }

            int productIndex = productView.getSelectedRow();
            Integer productId = Integer.parseInt(productView
                    .getValueAt(productIndex, 0).toString());

            if (quantity > storeProductService.get(productId).getSoluong()) {
                JOptionPane.showMessageDialog(this, "số lượng quá lớn");
                return;
            }

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "số lượng không hợp lệ");
                return;
            }

            if (this.cart.getProducts().containsKey(productId)) {
                CartDetail cartDetail = this.cart.getProducts().get(productId);

                Integer currentQuantity = this.cart.getProducts().get(productId).getQuantity();

                storeProductService.refreshAdd(cartDetail, quantity);

                this.cart.getProducts().get(productId).setQuantity(currentQuantity + quantity);
            } else {
                CartDetail cartDetail = new CartDetail(quantity);
                SPCTModel product = spctRepository.findById(productId);
                GiaModel price = giaRepository.getgiaMoiNhat(productId);

                KhuyenMai coupon = new KhuyenMai();

                cartDetail.setProduct(product);
                cartDetail.setPrice(price);
                cartDetail.setQuantity(quantity);

                storeProductService.refreshAdd(cartDetail, quantity);

                this.cart.getProducts().put(productId, cartDetail);
            }

            fillCart();
        }
    }//GEN-LAST:event_productViewMouseClicked

    private void cancelInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelInvoiceActionPerformed

        if (this.cart == null) {
            JOptionPane.showMessageDialog(this, " hãy chọn hóa trước");
            return;
        }

        if (this.cart.getProducts().isEmpty()) {

            cartService.remove(this.cart.getInvoiceId());

            JOptionPane.showMessageDialog(this, "đã hủy hóa đơn tróng");

            this.cart = null;

            return;
        }

        KhachHangModel customer = khachHangConnection.findByNameAndPhoneNumber(customerName.getText().trim(), customerPhoneNumber.getText().trim());

        if (customer == null) {
            customer = new KhachHangModel();

            customer.setSdt(customerPhoneNumber.getText().trim());
            customer.setTen(customerName.getText().trim());
            khachHangConnection.add(customer);
            customer = khachHangConnection.findByNameAndPhoneNumber(customer.getTen(), customer.getSdt());
        }
        this.cart.setCustomerId(customer.getMaKH());
        this.cart.setCustomerName(customer.getTen());
        this.cart.setCustomerPhoneNumber(customer.getSdt());

        this.cart.setSaver(LoginService.lg);
        this.cart.setPaymentMethod(paymentMethod.getSelectedItem().toString().trim());

        String invoiceId = hoaDonService.taoHoaDon(this.cart, TrangThaiHoaDon.DA_HUY);

        if (invoiceId != null) {
            JOptionPane.showMessageDialog(this, "hủy hóa đơn " + this.cart.getInvoiceId() + " thành  công");

            this.cart = null;
            this.cartService.remove(invoiceId);
        }

        clear();
        fillCart();

    }//GEN-LAST:event_cancelInvoiceActionPerformed

    private void paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentActionPerformed

        // ignored
        // no invoice selected
        if (this.cart == null) {
            JOptionPane.showMessageDialog(this, " hãy chọn hóa đơn trước");
            return;
        }

        // ignored
        // invoice have no products
        if (this.cart.getProducts().isEmpty()) {
            JOptionPane.showMessageDialog(this, "không thể thanh toán hóa đơn trống");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "xác nhân thanh toán hóa đơn : " + this.cart.getInvoiceId()
                , "xác nhận", JOptionPane.YES_NO_OPTION
                , JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
            return;
        }

        KhachHangModel customer = khachHangConnection.findByNameAndPhoneNumber(customerName.getText().trim(), customerPhoneNumber.getText().trim());

        if (customer == null) {
            customer = new KhachHangModel();

            customer.setSdt(customerPhoneNumber.getText().trim());
            customer.setTen(customerName.getText().trim());
            khachHangConnection.add(customer);
            customer = khachHangConnection.findByNameAndPhoneNumber(customer.getTen(), customer.getSdt());
        }
        this.cart.setCustomerId(customer.getMaKH());
        this.cart.setCustomerName(customer.getTen());
        this.cart.setCustomerPhoneNumber(customer.getSdt());

        this.cart.setSaver(LoginService.lg);
        this.cart.setPaymentMethod(paymentMethod.getSelectedItem().toString().trim());

        // save invoice to DB
        String invoiceId = hoaDonService.taoHoaDon(this.cart, TrangThaiHoaDon.DA_THANH_TOAN);


        if (invoiceId == null) {
            JOptionPane.showMessageDialog(this, "thanh toán hóa đơn " + cart.getInvoiceId() + " thất bại");
            return;
        }

        JOptionPane.showMessageDialog(this, "thanh toán hóa đơn " + cart.getInvoiceId() + " thành công");

        // export invoice
        int exportInvoice = JOptionPane.showConfirmDialog(this, "bạn có muốn xuất hóa đơn không", "xác nhận"
                , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (exportInvoice == JOptionPane.YES_OPTION) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(this);
            try {
                hoaDonService.export(this.cart.getInvoiceId(), fileChooser.getSelectedFile().toString());
                JOptionPane.showMessageDialog(this, "Xuất hóa đơn " + this.cart.getInvoiceId() + " thành công");
            } catch (java.io.IOException e) {
                JOptionPane.showMessageDialog(this, "Xuất hóa đơn " + this.cart.getInvoiceId() + " thất bại");
            }
        }

        this.cart = null;
        this.cartService.remove(invoiceId);

        clear();
        fillCart();
    }//GEN-LAST:event_paymentActionPerformed

    private void refreshProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshProductsActionPerformed
        this.storeProductService.fetch();
        this.findByProductName.setText("");
        loadProductView();
    }//GEN-LAST:event_refreshProductsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField applyVoucher;
    private javax.swing.JComboBox<String> availableVoucher;
    private javax.swing.JTextField buy;
    private javax.swing.JButton cancelInvoice;
    private javax.swing.JTable cartView;
    private javax.swing.JTextField change;
    private javax.swing.JButton createInvoice;
    private javax.swing.JTextField creationDate;
    private javax.swing.JTextField customerName;
    private javax.swing.JTextField customerPhoneNumber;
    private javax.swing.JTextField finalPrice;
    private javax.swing.JTextField findByProductName;
    private javax.swing.JTextField invoiceId;
    private javax.swing.JTable invoiceView;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton payment;
    private javax.swing.JComboBox<String> paymentMethod;
    private javax.swing.JTable productView;
    private javax.swing.JButton refresh;
    private javax.swing.JButton refreshProducts;
    private javax.swing.JTextField totalPrice;
    // End of variables declaration//GEN-END:variables

}
