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

/**
 *
 * @author Admin
 */
public class Quan_Ly_San_Pham extends javax.swing.JFrame {

    private SanPham_Service sp = new SanPham_Service();
    private SIZE_Service ss = new SIZE_Service();
    private Mau_Service ms = new Mau_Service();
    private SanPhamService_CT sps = new SanPhamService_CT();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = -1;

    
    private int maSPCT;

    private SanPhamViewModelProvider sanPhamViewModelProvider;

    private SanPhamRepository sanPhamRepository;

    private SPCTRepository spctRespository;

    private GiaRepository giaRepository;

    private SizeRepository sizeRepository;

    private MauSacRepository mauSacRepository;

    private SPCTViewModelProvider spctViewModelProvider;

    private LSGViewModelProvider lsgViewModelProvider;

    public Quan_Ly_San_Pham() {
        initComponents();

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

        
//        this.FillTableSanPham_CT(sps.getAll());
//        this.fillTbaleSanPham(sp.getAll());

       this.loadTableSP(sp.getAll());
        loadTableSPCT();
    }

    public void loadTableSPCT() {
        tbl_SanPham_CT.setModel(spctViewModelProvider.toTableModel());

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
        model = (DefaultTableModel)Tbl_SanPham.getModel();
        model.setRowCount(0);
        for (SanPhamModel x : list) {
            model.addRow(x.toDataRow());
        }
    }
   




    public void ClearFrom_SP() {
        txt_Ma.setText("");
        txt_Ten.setText("");
        buttonGroup1.clearSelection();
    }

    public void clearFrom() {
        txt_MaSP.setText("");
        txt_TenSP.setText("");
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
        txt_TenSP.setText(spctViewModel.getTenSP());
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
        txt_Ten.setText(spvm.getTenSanPham());

        if ((spvm.getTrangThai().compareTo("�?ang bán") == 0)) {
            rd_Dangban.setSelected(true);
        } else {
            rd_Dungban.setSelected(true);
        }
    }

    public SPCTModel readFromSPCT() {

        SPCTViewModel spctvm = spctViewModelProvider.SPCTViewModel().get(index);

        SizeModel size = sizeRepository.findByTen(cbo_Size.getSelectedItem()
                .toString().trim());

        MauSacModel mauSac =  mauSacRepository.findByTen(cbo_Mau_Sac.getSelectedItem()
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
        sp.setTensp(txt_Ten.getText().trim());
        sp.setTrangthai(rd_Dangban.isSelected() ? "?ang b�n"
                : "D?ng b�n");
        return sp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Ten = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_Ma = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_SanPham_CT = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        id_Ma_Tk = new javax.swing.JTextField();
        id_Ten_TK = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_TenSP = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_Gia = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_update_spct = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txt_MaSP = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        cbo_Size = new javax.swing.JComboBox<>();
        cbo_Mau_Sac = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_LSG = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Tên sản phẩm:");

        jLabel6.setText("Mã sản phẩm");

        txt_Ma.setEditable(false);

        jButton5.setText("Hiện sản phẩm đã ẩn");

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
                                    .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(id_Ten_CanTim, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(id_Ma_SP_CanTim))
                        .addGap(0, 130, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(20, 20, 20))
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
                    .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
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
                .addGap(224, 224, 224)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id_Ma_Tk, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(id_Ten_TK, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Tên sản phẩm :");

        jLabel3.setText("Số lương:");

        jLabel4.setText("Size:");

        jLabel5.setText("Màu sắc: ");

        txt_TenSP.setEditable(false);

        jLabel8.setText("Giá:");

        jButton1.setText("Thêm SP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_SoLuong))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btn_update_spct, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbo_Size, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbo_Mau_Sac, javax.swing.GroupLayout.Alignment.LEADING, 0, 102, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(cbo_Mau_Sac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cbo_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản phẩm chi tiết", jPanel1);

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
        jScrollPane3.setViewportView(tbl_LSG);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Lịch sử giá sản phẩm");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1294, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch sử thay đổi giá sản phẩm", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (Optional.ofNullable(spctRespository.add(readFromSPCT()))
                .isPresent()) {
            GiaModel gia = readGia();
            gia.setMaSPCT(spctRespository.count());

            giaRepository.add(gia);

            JOptionPane.showMessageDialog(this, "Thêm thành công");
            // this.FillTableSanPham_CT(sps.getAll());
            clearFrom();
            loadTableSPCT();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        index = Tbl_SanPham.getSelectedRow();
        if (index > -1) {
            ShowFrom_SP();
        }
    }//GEN-LAST:event_Tbl_SanPhamMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        clearFrom();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(sp.ADD_SP(this.readFrom_SP())>0){
            JOptionPane.showMessageDialog(this, "Th�m th�nh c�ng");
            this.loadTableSP(sp.getAll());
        }else{
            JOptionPane.showMessageDialog(this, "Th�m th?t b?i");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        clearFrom();
        loadTableSPCT();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbo_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_SizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_SizeActionPerformed

    private void id_Ten_TKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_Ten_TKKeyReleased
//        // TODO add your handling code here:
//        String ten = id_Ten_TK.getText();
//        if (ten.isEmpty()) {
//            this.loadTableSPCT(sps.getAll());
//        } else {
//            List<SPCTModel> list1 = sps.TimSanPham(ten);
//            this.loadTableSPCT(list1);
//        }
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

    private void tbl_SanPham_CTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPham_CTMouseClicked
        // TODO add your handling code here:
        index = tbl_SanPham_CT.getSelectedRow();
        if (index > -1) {
            ShowFrom_SPCT();
        }
    }//GEN-LAST:event_tbl_SanPham_CTMouseClicked

    private void btn_update_spctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_spctActionPerformed
        // TODO add your handling code here:
        SPCTModel spctModel = readFromSPCT();

        spctModel.setMaSPCT(this.maSPCT);

        System.out.println("ma spct: " + this.maSPCT);

        if (spctRespository.update((spctModel))) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            // this.fillTbaleSanPham(sp.getAll());

            GiaModel gia = readGia();
            gia.setMaSPCT(this.maSPCT);

            giaRepository.add(gia);

            loadTableSPCT();
            clearFrom();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }

    }//GEN-LAST:event_btn_update_spctActionPerformed

    private void btn_update_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_spActionPerformed
        // TODO add your handling code here:
        index = Tbl_SanPham.getSelectedRow();
        int ma = (int) Tbl_SanPham.getValueAt(index, 0);
        if(sp.Update_SP(this.readFrom_SP(), ma)>0){
            JOptionPane.showMessageDialog(this, "S?a th�nh c�ng");
            this.loadTableSP(sp.getAll());
        }else{
            JOptionPane.showMessageDialog(this, "S?a th?t b?i");
        }
    }//GEN-LAST:event_btn_update_spActionPerformed

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
    private javax.swing.JTable Tbl_SanPham;
    private javax.swing.JButton btn_update_sp;
    private javax.swing.JButton btn_update_spct;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_Mau_Sac;
    private javax.swing.JComboBox<String> cbo_Size;
    private javax.swing.JTextField id_Ma_SP_CanTim;
    private javax.swing.JTextField id_Ma_Tk;
    private javax.swing.JTextField id_Ten_CanTim;
    private javax.swing.JTextField id_Ten_TK;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rd_Dangban;
    private javax.swing.JRadioButton rd_Dungban;
    private javax.swing.JTable tbl_LSG;
    private javax.swing.JTable tbl_SanPham_CT;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_Ma;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_Ten;
    private javax.swing.JTextField txt_TenSP;
    // End of variables declaration//GEN-END:variables

}
