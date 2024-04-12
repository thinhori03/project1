package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.HoaDonViewModel;
import org.project1.nhom8.model.HDCTModel;
import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.HDCTRepository;
import org.project1.nhom8.repository.HoaDonRepository;
import org.project1.nhom8.repository.KhachHangConnection;
import org.project1.nhom8.repository.VoucherRepository;
import org.project1.nhom8.service.NhanVienService;
import org.project1.nhom8.util.data.convert.DateFormat;
import org.project1.nhom8.util.data.convert.DefaultConverter;
import org.project1.nhom8.util.data.visual.DataHeader;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HDViewModelProvider {

    private final HoaDonRepository hoaDonRepository;

    private final KhachHangConnection khachHangConnection;

    private final NhanVienService nhanVienService;

    private final HDCTRepository hdctRepository;

    private final GiaRepository giaRepository;

    private final VoucherRepository voucherRepository;

    public HDViewModelProvider() {
        this.hoaDonRepository = new HoaDonRepository();

        this.khachHangConnection = new KhachHangConnection();

        this.nhanVienService = new NhanVienService();

        this.hdctRepository = new HDCTRepository();

        this.giaRepository = new GiaRepository();

        this.voucherRepository = new VoucherRepository();
    }

    public List<HoaDonViewModel> getHoaDonViewModel() {
        return this.getModels(hoaDonRepository.findAll());
    }

    public DefaultTableModel toTableMode(List<HoaDonViewModel> viewModels) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();

        List<Field> fields = Arrays.stream(HoaDonViewModel.class.getDeclaredFields())
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .toList();


        defaultTableModel.setColumnCount(0);
        for (Field f : fields) {
            defaultTableModel.addColumn(f.getAnnotation(DataHeader.class).name());
        }

        defaultTableModel.setRowCount(0);

        List<String> rowData = new ArrayList<>();

        for (HoaDonViewModel gia : viewModels) {
            rowData = new ArrayList<>();
            try {
                for (Field j : fields) {
                    j.setAccessible(true);
                    if (j.getType().equals(Date.class)
                            && j.isAnnotationPresent(DateFormat.class)) {
                        rowData.add(DefaultConverter.VietnameseDateFormat((Date) j.get(gia)));
                    } else {
                        rowData.add(j.get(gia).toString());
                    }
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

            defaultTableModel.addRow(rowData.toArray());
        }

        return defaultTableModel;
    }

    public List<HoaDonViewModel> getModels(List<HoaDonModel> models) {
        List<HoaDonViewModel> result = new ArrayList<>();

        HoaDonViewModel hdvm = null;

        for (HoaDonModel hdm : models) {

            hdvm = new HoaDonViewModel();

            hdvm.setMaHoaDon(hdm.getMaHoaDon());
            hdvm.setNgayTao(hdm.getNgayTao());
            hdvm.setNgayThanhToan(hdm.getNgayThanhToan());
            hdvm.setTenKH(khachHangConnection.getTenByMa(hdm.getMaKH()));
            hdvm.setMaNV(hdm.getMaNV());
            hdvm.setMaNvXacNhan(hdm.getMaNVXacNhan());

            if (hdm.getMaVoucher() != null) {
                hdvm.setVoucher(voucherRepository.findById(hdm.getMaVoucher()).getGiaTri());
            } else {
                hdvm.setVoucher((double) 0);
            }

            hdvm.setTrangThai(hdm.getTrangThai());

            Double totalPrice = Double.valueOf(0);

            for (HDCTModel hdct : hdctRepository.findBymaHD(hdm.getMaHoaDon())) {
                totalPrice += (hdct.getSoLuong() * giaRepository.findById(hdct.getMaLSG()).getGia());
            }

            hdvm.setTongTien(totalPrice);

            result.add(hdvm);
        }

        return result;
    }
}
