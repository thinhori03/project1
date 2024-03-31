/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.project1.nhom8.View;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.provider.SPCTViewModelProvider;
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
public class QL_SanPham1 extends javax.swing.JPanel {

    private SIZE_Service ss = new SIZE_Service();
    private Mau_Service ms = new Mau_Service();
    private SanPham_Service gs = new SanPham_Service();
    private SanPhamService_CT sanpham = new SanPhamService_CT();
    private DefaultTableModel model = new DefaultTableModel();
    private int index = -1;

    private SanPhamRepository sanPhamRepository;

    private SPCTRepository spctRespository;

    private GiaRepository giaRepository;

    private SizeRepository sizeRepository;

    private MauSacRepository mauSacRepository;

    private SPCTViewModelProvider spctViewModelProvider;

    public QL_SanPham1() {
        initComponents();

        this.sanPhamRepository = new SanPhamRepository();

        this.spctRespository = new SPCTRepository();

        this.giaRepository = new GiaRepository();

        this.sizeRepository = new SizeRepository();

        this.mauSacRepository = new MauSacRepository();

        spctViewModelProvider = new SPCTViewModelProvider();

        loadTable();
    }

    public void loadTable() {
        Tbl_SanPham.setModel(spctViewModelProvider.toTableModel());
    }

    public void fillTable(List<SPCTModel> list) {
        model = (DefaultTableModel) Tbl_SanPham.getModel();
        model.setRowCount(0);
        for (SPCTModel sp : list) {
//            model.addRow(sp.toDataRow());
        }
    }

    public void ShowFrom() {
        String trangthai;
        index = Tbl_SanPham.getSelectedRow();
        Integer maSPCT = Integer.parseInt(Tbl_SanPham.getValueAt(index, 0).toString());

        SPCTViewModel spct = this.spctViewModelProvider.SPCTViewModel()
                        .get(index);

        txt_Ten.setText(spct.getTenSP());

        txt_Soluong.setText(spct.getSoLuong() + "");

        txt_Size.setText(spct.getSize());

        txt_MauSac.setText(spct.getMauSac());

        trangthai = spct.getTrangThai();

        if (trangthai.equalsIgnoreCase("�?ang bán")) {
            rd_Dangban.setSelected(true);
        } else if (trangthai.equalsIgnoreCase("Dừng bán")) {
            rd_Dungban.setSelected(true);
        }

        txt_LichSuGia.setText(spct.getGia() + "");
    }

    SPCTModel readFrom() {

        SPCTModel spct = new SPCTModel();
        
        SizeModel size = sizeRepository.findByTen(txt_Size.getText().trim());

        MauSacModel mauSac = mauSacRepository.findByTen(txt_MauSac.getText().trim());

        spct.setSoluong(Integer.parseInt(txt_Soluong.getText().trim()));
        spct.setMasize(size.getMasize());
        // spct.setTrangThai(rd_Dangban.isSelected() ? "�?ang bán" : "Dừng bán");
        spct.setMaMauSac(mauSac.getMamau());

//        sp.setTen(txt_Ten.getText());
//        sp.setSoluong(Integer.parseInt(txt_Soluong.getText()));
//        sp.setSize(ss.getSize(txt_Size.getText().trim()));
//        sp.setMasac(ms.getMaMau(txt_MauSac.getText().trim()));
//        sp.setMakm(txt_MaKM.getText());
//        if (rd_Dangban.isSelected()) {
//            sp.setTrangthai();
//        } else if (rd_Dungban.isSelected()) {
//            sp.setTrangthai("Dừng bán");
//        }
//        g.setId_gia(gs.TaoMA());
//        g.setGia(Float.parseFloat(txt_LichSuGia.getText().trim()));
//
//        g.setNgaybatdau(new Date());
//        g.setNgayketthuc(new Date());
//        gs.ADDGia(g);
//        sp.setId_gia(g.getId_gia());
        return spct;
    }

    public void clear() {
        txt_MaKM.setText("");
        txt_Ten.setText("");
        txt_Soluong.setText("");
        txt_Size.setText("");
        txt_MauSac.setText("");
        txt_LichSuGia.setText("");
        buttonGroup1.clearSelection();
    }

    public boolean check() {
        if (txt_Ten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống");
            return false;
        }

        try {
            String input = txt_Soluong.getText();
            if (input.equals("")) {
                JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
                return false;
            }
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập số nguyên");
            return false;
        }
        try {
            String input = txt_Size.getText();
            if (input.equals("")) {
                JOptionPane.showMessageDialog(this, "Size không được để trống");
                return false;
            }
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập số nguyên");
            return false;
        }
        if (txt_MauSac.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Màu sắc không được để trống");
            return false;
        }
        if (txt_MaKM.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã khuyến mại không được để trống");
            return false;
        }
        if (!rd_Dangban.isSelected() && !rd_Dungban.isSelected()) {
            JOptionPane.showMessageDialog(this, "Trạng thái không được để trống");
            return false;
        }
        try {
            String input = txt_LichSuGia.getText();
            if (input.equals("")) {
                JOptionPane.showMessageDialog(this, "Giá không được để trống");
                return false;
            }
            Float.parseFloat(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập số nguyên");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_ADD = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        txtTen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tbl_SanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        id_MaSP = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_Ten = new javax.swing.JTextField();
        txt_Soluong = new javax.swing.JTextField();
        txt_Size = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_MauSac = new javax.swing.JTextField();
        txt_MaKM = new javax.swing.JTextField();
        txt_LichSuGia = new javax.swing.JTextField();
        rd_Dangban = new javax.swing.JRadioButton();
        rd_Dungban = new javax.swing.JRadioButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Sản Phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btn_ADD.setText("Thêm ");
        btn_ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ADDActionPerformed(evt);
            }
        });

        btn_Update.setText("Sửa ");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Delete.setText("Xóa ");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });
        txtTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKeyReleased(evt);
            }
        });

        jLabel10.setText("Tìm kiếm sản phẩm theo tên");
        jLabel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SPCT", "Tên SP", "Số lượng", "Size", "Màu sắc", "Mã KM", "Trạng thái", "Lịch sử giá"
            }
        ));
        Tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tbl_SanPham);

        jLabel2.setText("Tìm sản phẩm theo mã");
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        id_MaSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_MaSPKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ADD)
                .addGap(120, 120, 120)
                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(id_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ADD)
                    .addComponent(btn_Update)
                    .addComponent(btn_Delete))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Tên SP:");

        jLabel4.setText("Số lượng:");

        jLabel6.setText("Màu sắc:");

        jLabel7.setText("Mã KM:");

        jLabel8.setText("Trạng thái:");

        jLabel9.setText("Lịch sử giá:");

        jLabel5.setText("Size:");

        txt_LichSuGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_LichSuGiaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd_Dangban);
        rd_Dangban.setText("�?ang bán");

        buttonGroup1.add(rd_Dungban);
        rd_Dungban.setText("Dừng bán");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_Soluong)
                        .addComponent(txt_Size, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                    .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_LichSuGia, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rd_Dangban, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(rd_Dungban, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_MaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rd_Dangban)
                            .addComponent(rd_Dungban, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_Soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(txt_LichSuGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(305, 305, 305))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ADDActionPerformed
        // TODO add your handling code here:
        if (check()) {
            if (spctRespository.add(readFrom()) != null) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                this.fillTable(sanpham.getAll());
                clear();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        }
    }//GEN-LAST:event_btn_ADDActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
//        if (check()) {
//            index = Tbl_SanPham.getSelectedRow();
//            int ma = (int) Tbl_SanPham.getValueAt(index, 0);
//            if (sanpham.UpdateSanPham(ma, this.readFrom()) > 0) {
//                JOptionPane.showMessageDialog(this, "Sửa thành công ");
//                this.fillTable(sanpham.getAll());
//                clear();
//            } else {
//                JOptionPane.showMessageDialog(this, "Sửa thất bại");
//            }
//        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void txt_LichSuGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_LichSuGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_LichSuGiaActionPerformed

    private void txtTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKeyReleased
        // TODO add your handling code here:
        String ten = txtTen.getText();
        if (ten.isEmpty()) {
            this.fillTable(sanpham.getAll());
        } else {
            List<SPCTModel> list1 = sanpham.TimSanPham(ten);
            this.fillTable(list1);
        }

        //        List<SanPham> list1 = sanpham.TimSanPham(ten);
        //        this.fillTable(list1);
    }//GEN-LAST:event_txtTenKeyReleased

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        // TODO add your handling code here:
//        index = Tbl_SanPham.getSelectedRow();
//        int ma = (int) Tbl_SanPham.getValueAt(index, 0);
//        if (sanpham.DeleteSanPham(ma) > 0) {
//            JOptionPane.showMessageDialog(this, "Sửa thành công ");
//            this.fillTable(sanpham.getAll());
//            clear();
//        } else {
//            JOptionPane.showMessageDialog(this, "Sửa thất bại");
//        }
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void Tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_SanPhamMouseClicked
        // TODO add your handling code here:
        index = Tbl_SanPham.getSelectedRow();
        if (index > -1) {
            ShowFrom();
        }
    }//GEN-LAST:event_Tbl_SanPhamMouseClicked

    private void id_MaSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_MaSPKeyReleased
        // TODO add your handling code here:
        String text = id_MaSP.getText();
        if (text.isEmpty()) {
            this.fillTable(sanpham.getAll());
        } else {
            try {
                int ma = Integer.parseInt(id_MaSP.getText());
                List<SPCTModel> list1 = sanpham.TimSanPham(ma);
                this.fillTable(list1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Cần phải nhập số nguyên");
            }
        }
    }//GEN-LAST:event_id_MaSPKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tbl_SanPham;
    private javax.swing.JButton btn_ADD;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField id_MaSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rd_Dangban;
    private javax.swing.JRadioButton rd_Dungban;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txt_LichSuGia;
    private javax.swing.JTextField txt_MaKM;
    private javax.swing.JTextField txt_MauSac;
    private javax.swing.JTextField txt_Size;
    private javax.swing.JTextField txt_Soluong;
    private javax.swing.JTextField txt_Ten;
    // End of variables declaration//GEN-END:variables
}
