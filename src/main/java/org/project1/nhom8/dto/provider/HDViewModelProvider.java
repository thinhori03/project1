package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.HoaDonViewModel;
import org.project1.nhom8.model.HoaDonModel;
import org.project1.nhom8.repository.HoaDonRepository;
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

public class HDViewModelProvider {

    private final HoaDonRepository hoaDonRepository;

    public HDViewModelProvider() {
        this.hoaDonRepository = new HoaDonRepository();
    }

    public List<HoaDonViewModel> getHoaDonViewModel() {

        List<HoaDonViewModel> result = new ArrayList<>();

        List<HoaDonModel> hoaDonModels = hoaDonRepository.findAll();

        HoaDonViewModel hdvm = null;

        for (HoaDonModel hdm : hoaDonModels) {

            hdvm = new HoaDonViewModel();

            hdvm.setNgayTao(hdm.getNgayTao());
            hdvm.setMaKH(hdm.getMaKH());
            hdvm.setMaNV(hdm.getMaNV());
            hdvm.setTrangThai(hdm.getTrangThai());
            hdvm.setTongTien(Double.valueOf(1000));

            result.add(hdvm);
        }

        return result;
    }

    public DefaultTableModel toTableMode() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();

        List<Field> fields = Arrays.asList(HoaDonViewModel.class.getDeclaredFields())
                .stream()
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .collect(Collectors.toList());


        defaultTableModel.setColumnCount(0);
        for (Field f : fields) {
            defaultTableModel.addColumn(f.getAnnotation(DataHeader.class).name());
        }

        defaultTableModel.setRowCount(0);

        List<String> rowData = new ArrayList<>();

        for (HoaDonViewModel gia : this.getHoaDonViewModel()) {
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
