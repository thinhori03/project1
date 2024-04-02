package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.VoucherViewModel;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.VoucherRepository;
import org.project1.nhom8.util.data.convert.DateFormat;
import org.project1.nhom8.util.data.convert.DefaultConverter;
import org.project1.nhom8.util.data.visual.DataHeader;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VoucherViewModelProvider {

    private final VoucherRepository voucherRepository;

    public VoucherViewModelProvider() {
        this.voucherRepository = new VoucherRepository();
    }

    public List<VoucherViewModel> getVoucherViewModel() {
        List<VoucherViewModel> result = new ArrayList<>();

        List<VoucherModel> voucherModels = voucherRepository.findAll();

        VoucherViewModel vvm = null;

        for (VoucherModel vm : voucherModels) {

            vvm = new VoucherViewModel();

            vvm.setMaVoucher(vm.getMaVoucher());
            vvm.setGia(vm.getGiaTri());
            vvm.setDieuKien(vm.getDiauKien());
            vvm.setNgayTao(vm.getNgayTao());
            vvm.setNgayCapNhat(vm.getNgayCapNhat());
            vvm.setSoLuong(vm.getSoLuong());
            vvm.setNgayBatDau(vm.getNgayBatDau());
            vvm.setNgayKetThuc(vm.getNgayKetThuc());
            vvm.setTrangThai(vm.getTrangThai());

            result.add(vvm);
        }

        return result;
    }

    public DefaultTableModel toTableMode() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();

        List<Field> fields = Arrays.asList(VoucherViewModel.class.getDeclaredFields())
                .stream()
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .collect(Collectors.toList());


        defaultTableModel.setColumnCount(0);
        for (Field f : fields) {
            defaultTableModel.addColumn(f.getAnnotation(DataHeader.class).name());
        }

        defaultTableModel.setRowCount(0);

        List<String> rowData = new ArrayList<>();

        for (VoucherViewModel gia : this.getVoucherViewModel()) {
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
}
