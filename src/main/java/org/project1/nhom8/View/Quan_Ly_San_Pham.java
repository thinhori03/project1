/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.project1.nhom8.View;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.SanPhamViewModel;
import org.project1.nhom8.dto.provider.LSGViewModelProvider;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
import org.project1.nhom8.dto.provider.SanPhamViewModelProvider;
import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.MauSacModel;
import org.project1.nhom8.model.SanPhamModel;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.model.SizeModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.MauSacRepository;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.repository.SizeRepository;
import org.project1.nhom8.service.SanPham_Service;
import org.project1.nhom8.service.Mau_Service;
import org.project1.nhom8.service.SIZE_Service;
import org.project1.nhom8.service.SanPhamService_CT;
import org.project1.nhom8.util.swing.GeneralDocumentListener;

/**
 *
 * @author Admin
 */
public class Quan_Ly_San_Pham extends javax.swing.JPanel {

    private SanPham_Service sp = new SanPham_Service();
    private SIZE_Service ss = new SIZE_Service();
    private Mau_Service ms = new Mau_Service();
    private SanPhamService_CT sps = new SanPhamService_CT();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = -1;

    private int maSPCT;

    private SPCTRepository sPCTRepository;

    private SanPhamViewModelProvider sanPhamViewModelProvider;

    private SanPhamRepository sanPhamRepository;

    private SPCTRepository spctRespository;

    private GiaRepository giaRepository;

    private SizeRepository sizeRepository;

    private MauSacRepository mauSacRepository;

    private SPCTViewModelProvider spctViewModelProvider;

    private LSGViewModelProvider lsgViewModelProvider;

    private Optional<SanPhamModel> osp = null;

    public Quan_Ly_San_Pham() {
        initComponents();

        this.sPCTRepository = new SPCTRepository();

        this.sanPhamViewModelProvider = new SanPhamViewModelProvider();

        this.sanPhamRepository = new SanPhamRepository();

        this.spctRespository = new SPCTRepository();

        this.giaRepository = new GiaRepository();

        this.sizeRepository = new SizeRepository();

        this.mauSacRepository = new MauSacRepository();

        spctViewModelProvider = new SPCTViewModelProvider();

        cbo_Size.setSelectedIndex(-1);
        cbo_Mau_Sac.setSelectedIndex(-1);
        this.sanPhamViewModelProvider = new SanPhamViewModelProvider();

        this.sanPhamRepository = new SanPhamRepository();

        this.spctRespository = new SPCTRepository();

        this.giaRepository = new GiaRepository();

        this.sizeRepository = new SizeRepository();

        this.mauSacRepository = new MauSacRepository();

        this.spctViewModelProvider = new SPCTViewModelProvider();

        this.lsgViewModelProvider = new LSGViewModelProvider();

        /**
         * txt_MaSP changed event
         */
        this.txt_MaSP.getDocument().addDocumentListener(new GeneralDocumentListener() {
            @Override
            public void onChange() {

                try {
                    osp = Optional.ofNullable(sanPhamRepository
                            .findById(Integer.parseInt(txt_MaSP
                                    .getText().trim())));
                } catch (Exception e) {
                    osp = Optional.empty();
                }

                osp.ifPresentOrElse(o -> txt_TenSPCT.setText(o.getTensp()),
                        () -> txt_TenSPCT.setText(""));

            }
        }
        );
        id_Ma_Tk.getDocument().addDocumentListener(new GeneralDocumentListener() {
            @Override
            public void onChange() {

                try {
                    loadTableSPCT(spctViewModelProvider.getViewModels(spctRespository
                            .findByMa(Integer.parseInt(id_Ma_Tk.getText().trim()))));

                } catch (Exception e) {
                    loadTableSPCT(spctViewModelProvider.SPCTViewModel());
                }

            }
        });

        id_Ten_TK.getDocument().addDocumentListener(new GeneralDocumentListener() {
            @Override
            public void onChange() {

                loadTableSPCT(spctViewModelProvider.getViewModels(spctRespository.findByTen(id_Ten_TK.getText().trim())));

            }
        });

//        this.FillTableSanPham_CT(sps.getAll());
//        this.fillTbaleSanPham(sp.getAll());
        this.loadTableSP(sp.getAll());
        loadTableSPCT(spctViewModelProvider.SPCTViewModel());
    }

    public void loadTableSPCT(List<SPCTViewModel> viewModels) {

        tbl_SanPham_CT.setModel(spctViewModelProvider.toTableModel(viewModels));

        cbo_Size.setModel(new DefaultComboBoxModel<>(
                sizeRepository.findAll().stream()
                        .map(o -> o.getTensize())
                        .toArray(String[]::new)
        ));

        cbo_Mau_Sac.setModel(new DefaultComboBoxModel<>(
                mauSacRepository.findAll().stream()
                        .map(o -> o.getTenmau())
                        .toArray(String[]::new)
        ));

        tbl_LSG.setModel(lsgViewModelProvider.toTableModel(-1));
    }

    public void loadTableSP(List<SanPhamModel> list) {
        model = (DefaultTableModel) Tbl_SanPham.getModel();
        model.setRowCount(0);
        for (SanPhamModel x : list) {
            model.addRow(x.toDataRow());
        }
    }

    public void ClearFrom_SP() {
        txt_Ma.setText("");
        txt_TenSP.setText("");
        buttonGroup1.clearSelection();
    }

    public void clearFrom() {
        txt_MaSP.setText("");
        txt_TenSPCT.setText("");
        txt_SoLuong.setText("");
        cbo_Size.setSelectedIndex(-1);
        cbo_Mau_Sac.setSelectedIndex(-1);
        txt_Gia.setText("");
    }

    public void ShowFrom_SPCT() {

        index = tbl_SanPham_CT.getSelectedRow();

        SPCTViewModel spctViewModel = spctViewModelProvider.SPCTViewModel()
                .get(index);

        this.maSPCT = spctViewModel.getMaSPCT();
        txt_MaSP.setText(spctViewModel.getMaSP() + "");
        txt_TenSPCT.setText(spctViewModel.getTenSP());
        txt_SoLuong.setText(spctViewModel.getSoLuong() + "");
        cbo_Size.setSelectedItem(spctViewModel.getSize());
        cbo_Mau_Sac.setSelectedItem(spctViewModel.getMauSac());
        txt_Gia.setText(spctViewModel.getGia() + "");

        tbl_LSG.setModel(lsgViewModelProvider.toTableModel(spctViewModel.getMaSPCT()));
    }

    public void ShowFrom_SP() {
        index = Tbl_SanPham.getSelectedRow();

        SanPhamViewModel spvm = sanPhamViewModelProvider.getSanPhamViewModel()
                .get(index);

        txt_Ma.setText(spvm.getMaSanPham() + "");
        txt_TenSP.setText(spvm.getTenSanPham());

        if ((spvm.getTrangThai().compareTo("Đang bán") == 0)) {
            rd_Dangban.setSelected(true);
        } else {
            rd_Dungban.setSelected(true);
        }
    }

    public SPCTModel readFromSPCT() {

        SizeModel size = sizeRepository.findByTen(cbo_Size.getSelectedItem()
                .toString().trim());

        MauSacModel mauSac = mauSacRepository.findByTen(cbo_Mau_Sac.getSelectedItem()
                .toString().trim());

        SPCTModel spct = new SPCTModel();
        spct.setMaSPCT(spctRespository.count() + 1);
        spct.setSoluong(Integer.parseInt(txt_SoLuong.getText().trim()));
        spct.setMasize(size.getMasize());
        spct.setMaMauSac(mauSac.getMamau());
        spct.setSoluong(Integer.parseInt(txt_SoLuong.getText().trim()));
        spct.setMaSP(Integer.parseInt(txt_MaSP.getText().trim()));

        return spct;
    }

    /**
     * prepare to insert gia model
     */
    public GiaModel readGia() {

        GiaModel gia = new GiaModel();

        gia.setMaLSG(giaRepository.count() + 1);
        gia.setGia(Double.parseDouble(txt_Gia.getText().trim()));
        gia.setMaSPCT(this.maSPCT);
        gia.setNgayCapNhat(new Date());

        return gia;
    }

    SanPhamModel readFrom_SP() {
        SanPhamModel sp = new SanPhamModel();
        sp.setTensp(txt_TenSP.getText().trim());
        sp.setTrangthai(rd_Dangban.isSelected() ? "?ang bán"
                : "Dừng Bán");
        return sp;
    }

    public boolean check_SPCT() {
        String maSP = txt_MaSP.getText();
        try {
            if (maSP.equals("")) {
                JOptionPane.showMessageDialog(this, "Mã không được chống");
                return false;
            }
            Integer.parseInt(maSP);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bạn cần phải nhập số nguyên");
            return false;
        }
        String soluong = txt_SoLuong.getText();
        try {
            if (soluong.equals("")) {
                JOptionPane.showMessageDialog(this, "Số lượng không được chống");
                return false;
            }
            Integer.parseInt(soluong);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bạn cần phải nhập số nguyên");
            return false;
        }
        String Gia = txt_Gia.getText();
        try {
            if (Gia.equals("")) {
                JOptionPane.showMessageDialog(this, "Giá không được chống");
                return false;
            }
            Float.parseFloat(Gia);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bạn cần phải nhập số nguyên");
            return false;
        }
        return true;
    }

    public boolean Check_SP() {
        if (txt_TenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên không được để chống");
            return false;
        }
        if (!rd_Dangban.isSelected() && !rd_Dungban.isSelected()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn trạng thái");
            return false;
        }
        return true;
    }
    SizeModel readSIZE(String tensize){
        SizeModel size = new SizeModel();
        size.setTensize(tensize);
        return size;
    }
    MauSacModel readMAU(String tenmau){
        MauSacModel mau = new MauSacModel();
        mau.setTenmau(tenmau);
        return mau;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_TenSP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_Ma = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        id_Ma_SP_CanTim = new javax.swing.JTextField();
        id_Ten_CanTim = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btn_update_sp = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        rd_Dangban = new javax.swing.JRadioButton();
        rd_Dungban = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tbl_SanPham = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        id_Ma_Tk = new javax.swing.JTextField();
        id_Ten_TK = new javax.swing.JTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPham_CT = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_LSG = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_TenSPCT = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Gia = new javax.swing.JTextField();
        Btn_ThemSPCT = new javax.swing.JButton();
        btn_update_spct = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txt_MaSP = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        cbo_Size = new javax.swing.JComboBox<>();
        cbo_Mau_Sac = new javax.swing.JComboBox<>();
        btn_ADDSize = new javax.swing.JButton();
        btn_ADDMau = new javax.swing.JButton();

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Tên sản phẩm:");

        jLabel6.setText("Mã sản phẩm");

        txt_Ma.setEditable(false);

        jLabel11.setText("Tìm kiếm theo mã:");

        jLabel13.setText("Tìm kiếm theo tên:");

        id_Ma_SP_CanTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_Ma_SP_CanTimKeyReleased(evt);
            }
        });

        id_Ten_CanTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_Ten_CanTimKeyReleased(evt);
            }
        });

        jButton6.setText("Làm mới");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Thêm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btn_update_sp.setText("Sửa ");
        btn_update_sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_spActionPerformed(evt);
            }
        });

        jLabel14.setText("Trạng thái:");

        buttonGroup1.add(rd_Dangban);
        rd_Dangban.setText("Đang bán");

        buttonGroup1.add(rd_Dungban);
        rd_Dungban.setText("Dừng bán");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Ma, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(btn_update_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rd_Dangban, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rd_Dungban, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(id_Ten_CanTim, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(id_Ma_SP_CanTim)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(264, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(id_Ma_SP_CanTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_Ten_CanTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rd_Dangban)
                        .addComponent(rd_Dungban))
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton6)
                    .addComponent(btn_update_sp)))
        );

        Tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Trạng Thái"
            }
        ));
        Tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tbl_SanPham);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Quản Lý Sản Phẩm");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(327, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel2);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Chi Tiết Sản Phẩm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(24, 24, 24))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setText("Tìm sản phẩm theo mã");
        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setText("Tìm sản phẩm theo tên");
        jLabel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        id_Ma_Tk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_Ma_TkKeyReleased(evt);
            }
        });

        id_Ten_TK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_Ten_TKKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_Ma_Tk, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(id_Ten_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_Ma_Tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(id_Ten_TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        tbl_SanPham_CT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Size", "Màu sắc", "Trạng thái", "Giá"
            }
        ));
        tbl_SanPham_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPham_CTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_SanPham_CT);

        jTabbedPane2.addTab("sản phẩm chi tiết", jScrollPane1);

        tbl_LSG.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbl_LSG);

        jTabbedPane2.addTab("Lịch sử thay đổi giá của sản phẩm", jScrollPane4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Tên sản phẩm :");

        jLabel3.setText("Số lương:");

        jLabel4.setText("Size:");

        jLabel5.setText("Màu sắc: ");

        txt_TenSPCT.setEditable(false);

        jLabel8.setText("Giá:");

        Btn_ThemSPCT.setText("Thêm SP");
        Btn_ThemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ThemSPCTActionPerformed(evt);
            }
        });

        btn_update_spct.setText("Sửa SP");
        btn_update_spct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_spctActionPerformed(evt);
            }
        });

        jLabel12.setText("Mã sản phẩm:");

        jButton3.setText("Làm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cbo_Size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "39", "40", "41", "42" }));
        cbo_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_SizeActionPerformed(evt);
            }
        });

        cbo_Mau_Sac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đỏ", "Đen", "Xám", " " }));

        btn_ADDSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btn_ADDSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDSizeActionPerformed(evt);
            }
        });

        btn_ADDMau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btn_ADDMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDMauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Btn_ThemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_SoLuong))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addComponent(btn_update_spct, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addComponent(jButton3)
                        .addGap(96, 96, 96))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbo_Size, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_Mau_Sac, javax.swing.GroupLayout.Alignment.LEADING, 0, 102, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_ADDMau, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_ADDSize, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                        .addContainerGap(217, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(cbo_Mau_Sac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cbo_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ADDSize))
                        .addGap(40, 40, 40)
                        .addComponent(btn_ADDMau)))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_ThemSPCT)
                    .addComponent(btn_update_spct)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(967, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbo_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_SizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_SizeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clearFrom();
        loadTableSPCT(spctViewModelProvider.SPCTViewModel());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_update_spctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_spctActionPerformed
        // TODO add your handling code here:
        SPCTModel spctModel = readFromSPCT();

        spctModel.setMaSPCT(this.maSPCT);

        System.out.println("ma spct: " + this.maSPCT);

        if (spctRespository.update((spctModel))) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            // this.fillTbaleSanPham(sp.getAll());

            GiaModel gia = readGia();
            gia.setMaSPCT(this.maSPCT);

            giaRepository.add(gia);

            loadTableSPCT(spctViewModelProvider.SPCTViewModel());
            clearFrom();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
    }//GEN-LAST:event_btn_update_spctActionPerformed

    private void Btn_ThemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ThemSPCTActionPerformed
        // TODO add your handling code here:
        if (check_SPCT()) {
            if (Optional.ofNullable(spctRespository.add(readFromSPCT()))
                .isPresent()) {
                GiaModel gia = readGia();
                gia.setMaSPCT(spctRespository.count());

                giaRepository.add(gia);

                JOptionPane.showMessageDialog(this, "Thêm thành công");
                clearFrom();
                loadTableSPCT(spctViewModelProvider.SPCTViewModel());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_Btn_ThemSPCTActionPerformed

    private void tbl_SanPham_CTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPham_CTMouseClicked
        // TODO add your handling code here:
        index = tbl_SanPham_CT.getSelectedRow();
        if (index > -1) {
            ShowFrom_SPCT();
        }
    }//GEN-LAST:event_tbl_SanPham_CTMouseClicked

    private void id_Ten_TKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ten_TKKeyReleased

    }//GEN-LAST:event_id_Ten_TKKeyReleased

    private void id_Ma_TkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ma_TkKeyReleased
        // TODO add your handling code here:
        //        String text = id_Ma_Tk.getText();
        //        if (text.isEmpty()) {
            //            this.FillTableSanPham_CT(sps.getAll());
            //        } else {
            //            try {
                //                int ma = Integer.parseInt(id_Ma_Tk.getText());
                //                List<SPCTModel> list1 = sps.TimSanPham(ma);
                //                this.FillTableSanPham_CT(list1);
                //            } catch (NumberFormatException e) {
                //                JOptionPane.showMessageDialog(this, "Cần phải nhập số nguyên");
                //            }
            //        }
    }//GEN-LAST:event_id_Ma_TkKeyReleased

    private void Tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        index = Tbl_SanPham.getSelectedRow();
        if (index > -1) {
            ShowFrom_SP();
        }
    }//GEN-LAST:event_Tbl_SanPhamMouseClicked

    private void btn_update_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_spActionPerformed
        // TODO add your handling code here:
        if (Check_SP()) {
            index = Tbl_SanPham.getSelectedRow();
            int ma = (int) Tbl_SanPham.getValueAt(index, 0);
            if (sp.Update_SP(this.readFrom_SP(), ma) > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                this.loadTableSP(sp.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btn_update_spActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (Check_SP()) {
            if (sp.ADD_SP(this.readFrom_SP()) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                this.loadTableSP(sp.getAll());
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        clearFrom();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void id_Ten_CanTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ten_CanTimKeyReleased
        // TODO add your handling code here:
        String ten = id_Ten_CanTim.getText();
        if (ten.isEmpty()) {
            this.loadTableSP(sp.getAll());
        } else {
            List<SanPhamModel> list1 = sp.TimSanPham(ten);
            this.loadTableSP(list1);
        }
    }//GEN-LAST:event_id_Ten_CanTimKeyReleased

    private void id_Ma_SP_CanTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ma_SP_CanTimKeyReleased
        // TODO add your handling code here:
        String text = id_Ma_SP_CanTim.getText();
        if (text.isEmpty()) {
            this.loadTableSP(sp.getAll());
        } else {
            try {
                int ma = Integer.parseInt(id_Ma_SP_CanTim.getText());
                List<SanPhamModel> list1 = sp.TimSanPham(ma);
                this.loadTableSP(list1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Cần phải nhập số nguyên");
            }
        }
    }//GEN-LAST:event_id_Ma_SP_CanTimKeyReleased

    private void btn_ADDSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADDSizeActionPerformed
        // TODO add your handling code here:
        String tensize= JOptionPane.showInputDialog(this,"Nhập size:");
        if(ss.ADD_SIZE(this.readSIZE(tensize))>0){
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTableSPCT(spctViewModelProvider.SPCTViewModel());
        }else{
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btn_ADDSizeActionPerformed

    private void btn_ADDMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADDMauActionPerformed
        // TODO add your handling code here:
         String tenmau= JOptionPane.showInputDialog(this,"Nhập màu:");
        if(ms.ADD_MAU(this.readMAU(tenmau))>0){
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTableSPCT(spctViewModelProvider.SPCTViewModel());
        }else{
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_btn_ADDMauActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_San_Pham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_San_Pham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_San_Pham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_San_Pham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            UIManager.setLookAndFeel(
                    "com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme"
            );
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            System.out.println("cannot aply look and feel");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quan_Ly_San_Pham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_ThemSPCT;
    private javax.swing.JTable Tbl_SanPham;
    private javax.swing.JButton btn_ADDMau;
    private javax.swing.JButton btn_ADDSize;
    private javax.swing.JButton btn_update_sp;
    private javax.swing.JButton btn_update_spct;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_Mau_Sac;
    private javax.swing.JComboBox<String> cbo_Size;
    private javax.swing.JTextField id_Ma_SP_CanTim;
    private javax.swing.JTextField id_Ma_Tk;
    private javax.swing.JTextField id_Ten_CanTim;
    private javax.swing.JTextField id_Ten_TK;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton rd_Dangban;
    private javax.swing.JRadioButton rd_Dungban;
    private javax.swing.JTable tbl_LSG;
    private javax.swing.JTable tbl_SanPham_CT;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_TenSP;
    private javax.swing.JTextField txt_TenSPCT;
    // End of variables declaration//GEN-END:variables

}
