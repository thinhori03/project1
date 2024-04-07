/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.project1.nhom8.View;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.SanPhamViewModel;
import org.project1.nhom8.dto.provider.LSGViewModelProvider;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
import org.project1.nhom8.dto.provider.SanPhamViewModelProvider;
import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.MauSacModel;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.model.SanPhamModel;
import org.project1.nhom8.model.SizeModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.MauSacRepository;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.repository.SizeRepository;
import org.project1.nhom8.service.Mau_Service;
import org.project1.nhom8.service.SIZE_Service;
import org.project1.nhom8.service.SanPhamService_CT;
import org.project1.nhom8.service.SanPham_Service;
import org.project1.nhom8.util.swing.GeneralDocumentListener;

/**
 *
 * @author Admin
 */
public class QL_SanPham extends javax.swing.JPanel {

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

    public QL_SanPham() {
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

    public void clearFrom() {
        txt_MaSP.setText("");
        txt_TenSPCT.setText("");
        txt_SoLuong.setText("");
        cbo_Size.setSelectedIndex(-1);
        cbo_Mau_Sac.setSelectedIndex(-1);
        txt_Gia.setText("");
        buttonGroup1.clearSelection();
    }

    public void ShowFrom_SPCT() {

        index = tbl_SanPham_CT.getSelectedRow();
        SanPhamViewModel spvm = sanPhamViewModelProvider.getSanPhamViewModel()
                .get(index);
        SPCTViewModel spctViewModel = spctViewModelProvider.SPCTViewModel()
                .get(index);

        this.maSPCT = spctViewModel.getMaSPCT();
        txt_MaSP.setText(spctViewModel.getMaSP() + "");
        txt_TenSPCT.setText(spctViewModel.getTenSP());
        txt_SoLuong.setText(spctViewModel.getSoLuong() + "");
        cbo_Size.setSelectedItem(spctViewModel.getSize());
        cbo_Mau_Sac.setSelectedItem(spctViewModel.getMauSac());
        txt_Gia.setText(spctViewModel.getGia() + "");
        if ((spvm.getTrangThai().compareTo("Đang bán") == 0)) {
            rd_Dangban.setSelected(true);
        } else {
            rd_Dungban.setSelected(true);
        }

        tbl_LSG.setModel(lsgViewModelProvider.toTableModel(spctViewModel.getMaSPCT()));
    }

    public void ShowFrom_SP() {
        index = Tbl_SanPham.getSelectedRow();

        SanPhamViewModel spvm = sanPhamViewModelProvider.getSanPhamViewModel()
                .get(index);

        txt_MaSP.setText(spvm.getMaSanPham() + "");
        txt_TenSPCT.setText(spvm.getTenSanPham());

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
        sp.setTensp(txt_TenSPCT.getText().trim());
        sp.setTrangthai(rd_Dangban.isSelected() ? "Đang bán"
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
        if (txt_TenSPCT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên không được để chống");
            return false;
        }
        if (!rd_Dangban.isSelected() && !rd_Dungban.isSelected()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn trạng thái");
            return false;
        }
        return true;
    }

    SizeModel readSIZE(String tensize) {
        SizeModel size = new SizeModel();
        size.setTensize(tensize);
        return size;
    }

    MauSacModel readMAU(String tenmau) {
        MauSacModel mau = new MauSacModel();
        mau.setTenmau(tenmau);
        return mau;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_TenSPCT = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_Gia = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_MaSP = new javax.swing.JTextField();
        cbo_Size = new javax.swing.JComboBox<>();
        cbo_Mau_Sac = new javax.swing.JComboBox<>();
        btn_ADDSize1 = new javax.swing.JButton();
        btn_ADDMau1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        rd_Dangban = new javax.swing.JRadioButton();
        rd_Dungban = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tbl_SanPham = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        id_Ma_Tk = new javax.swing.JTextField();
        id_Ten_TK = new javax.swing.JTextField();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_SanPham_CT = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_LSG = new javax.swing.JTable();
        id_Ma_CanTim = new javax.swing.JTextField();
        id_Ten_Cantim = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        Btn_ThemSPCT1 = new javax.swing.JButton();
        btn_update_spct1 = new javax.swing.JButton();
        btn_update_sp = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel16.setText("Tên sản phẩm :");

        jLabel17.setText("Số lượng:");

        jLabel18.setText("Size:");

        jLabel19.setText("Màu sắc: ");

        jLabel20.setText("Giá:");

        jLabel21.setText("Mã sản phẩm:");

        cbo_Size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "39", "40", "41", "42" }));
        cbo_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_SizeActionPerformed(evt);
            }
        });

        cbo_Mau_Sac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đỏ", "Đen", "Xám", " " }));

        btn_ADDSize1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btn_ADDSize1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDSize1ActionPerformed(evt);
            }
        });

        btn_ADDMau1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btn_ADDMau1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDMau1ActionPerformed(evt);
            }
        });

        jLabel14.setText("Trạng thái:");

        buttonGroup1.add(rd_Dangban);
        rd_Dangban.setText("Đang bán");

        buttonGroup1.add(rd_Dungban);
        rd_Dungban.setText("Dừng bán");

        jLabel11.setText("Tìm kiếm theo mã:");

        jLabel13.setText("Tìm kiếm theo tên:");

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

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel22.setText("Tìm sản phẩm theo mã");
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel23.setText("Tìm sản phẩm theo tên");
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_Ten_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_Ma_Tk)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(id_Ma_Tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(id_Ten_TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
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
        jScrollPane3.setViewportView(tbl_SanPham_CT);

        jTabbedPane3.addTab("sản phẩm chi tiết", jScrollPane3);

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
        jScrollPane5.setViewportView(tbl_LSG);

        jTabbedPane3.addTab("Lịch sử thay đổi giá của sản phẩm", jScrollPane5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        id_Ma_CanTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_Ma_CanTimKeyReleased(evt);
            }
        });

        id_Ten_Cantim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_Ten_CantimKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton7.setText("Thêm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        Btn_ThemSPCT1.setText("Thêm SPCT");
        Btn_ThemSPCT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ThemSPCT1ActionPerformed(evt);
            }
        });

        btn_update_spct1.setText("Sửa SPCT");
        btn_update_spct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_spct1ActionPerformed(evt);
            }
        });

        btn_update_sp.setText("Sửa ");
        btn_update_sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_spActionPerformed(evt);
            }
        });

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_update_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_ThemSPCT1)
                    .addComponent(btn_update_spct1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jButton7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Btn_ThemSPCT1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_update_spct1)
                            .addComponent(btn_update_sp)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton4)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_TenSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(txt_MaSP)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbo_Size, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbo_Mau_Sac, javax.swing.GroupLayout.Alignment.LEADING, 0, 102, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_ADDMau1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ADDSize1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(txt_Gia)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rd_Dangban, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(rd_Dungban, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(id_Ma_CanTim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_Ten_Cantim, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(468, 468, 468)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2502, 2502, 2502))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(btn_ADDSize1)
                                        .addGap(24, 24, 24)
                                        .addComponent(btn_ADDMau1))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21)
                                            .addComponent(cbo_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_TenSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel19)
                                            .addComponent(cbo_Mau_Sac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)
                                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(rd_Dangban)
                                    .addComponent(rd_Dungban)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(id_Ma_CanTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(id_Ten_Cantim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(561, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 77, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ThemSPCT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ThemSPCT1ActionPerformed
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
    }//GEN-LAST:event_Btn_ThemSPCT1ActionPerformed

    private void btn_update_spct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_spct1ActionPerformed
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
    }//GEN-LAST:event_btn_update_spct1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbo_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_SizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_SizeActionPerformed

    private void btn_ADDSize1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADDSize1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ADDSize1ActionPerformed

    private void btn_ADDMau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADDMau1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ADDMau1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (Check_SP()) {
            if (sp.ADD_SP(this.readFrom_SP()) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                this.loadTableSP(sp.getAll());
                clearFrom();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btn_update_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_spActionPerformed
        // TODO add your handling code here:
        if (Check_SP()) {
            index = Tbl_SanPham.getSelectedRow();
            int ma = (int) Tbl_SanPham.getValueAt(index, 0);
            if (sp.Update_SP(this.readFrom_SP(), ma) > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                this.loadTableSP(sp.getAll());
                clearFrom();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btn_update_spActionPerformed

    private void id_Ma_TkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ma_TkKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_id_Ma_TkKeyReleased

    private void id_Ten_TKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ten_TKKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_id_Ten_TKKeyReleased

    private void tbl_SanPham_CTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPham_CTMouseClicked
        // TODO add your handling code here:
        index = tbl_SanPham_CT.getSelectedRow();
        if (index > -1) {
            ShowFrom_SPCT();
        }
    }//GEN-LAST:event_tbl_SanPham_CTMouseClicked

    private void Tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        index = Tbl_SanPham.getSelectedRow();
        if (index > -1) {
            ShowFrom_SP();
        }

    }//GEN-LAST:event_Tbl_SanPhamMouseClicked

    private void id_Ma_CanTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ma_CanTimKeyReleased
        // TODO add your handling code here:
        String text = id_Ma_CanTim.getText();
        if (text.isEmpty()) {
            this.loadTableSP(sp.getAll());
        } else {
            try {
                int ma = Integer.parseInt(id_Ma_CanTim.getText());
                List<SanPhamModel> list1 = sp.TimSanPham(ma);
                this.loadTableSP(list1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Cần phải nhập số nguyên");
            }
        }
    }//GEN-LAST:event_id_Ma_CanTimKeyReleased

    private void id_Ten_CantimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ten_CantimKeyReleased
//         TODO add your handling code here:
        String text = id_Ten_Cantim.getText();
        if (text.isEmpty()) {
            this.loadTableSP(sp.getAll());
        } else {
            try {
                String ten  = id_Ten_Cantim.getText();
                List<SanPhamModel> list1 = sp.TimSanPham(ten);
                this.loadTableSP(list1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_id_Ten_CantimKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_ThemSPCT1;
    private javax.swing.JTable Tbl_SanPham;
    private javax.swing.JButton btn_ADDMau1;
    private javax.swing.JButton btn_ADDSize1;
    private javax.swing.JButton btn_update_sp;
    private javax.swing.JButton btn_update_spct1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_Mau_Sac;
    private javax.swing.JComboBox<String> cbo_Size;
    private javax.swing.JTextField id_Ma_CanTim;
    private javax.swing.JTextField id_Ma_Tk;
    private javax.swing.JTextField id_Ten_Cantim;
    private javax.swing.JTextField id_Ten_TK;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JRadioButton rd_Dangban;
    private javax.swing.JRadioButton rd_Dungban;
    private javax.swing.JTable tbl_LSG;
    private javax.swing.JTable tbl_SanPham_CT;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_TenSPCT;
    // End of variables declaration//GEN-END:variables
}
