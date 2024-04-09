package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.LSGViewModel;
import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.util.data.convert.DefaultConverter;
import org.project1.nhom8.util.data.visual.DataHeader;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LSGViewModelProvider {

    private final GiaRepository giaRepository;
    private final SanPhamRepository sanPhamRepository;

    private final SPCTRepository spctRepository;

    public LSGViewModelProvider() {
        giaRepository = new GiaRepository();

        sanPhamRepository = new SanPhamRepository();

        spctRepository = new SPCTRepository();
    }

    public List<LSGViewModel> getLSGViewModels(Integer maSPCT) {
        List<LSGViewModel> result = new ArrayList<>();

        List<GiaModel> giaModels = giaRepository.getLichSugia(maSPCT);

        /**
         * return empty list if cannot find LSG
         */
        if (Optional.ofNullable(giaModels).isEmpty()) {
            return result;
        }

        LSGViewModel giavm = null;

        for (GiaModel gia : giaModels) {

            giavm = new LSGViewModel();

            giavm.setMaSPCT(gia.getMaSPCT());

            giavm.setTenSPCT(sanPhamRepository.findById(spctRepository
                    .findById(gia.getMaSPCT()).getMaSP()).getTensp());

            giavm.setGia(gia.getGia());
            giavm.setNgayCapNhat(gia.getNgayCapNhat());
            giavm.setNgayKetThuc(gia.getNgayKetThuc());

            result.add(giavm);
        }

        return result;
    }

    public TableModel toTableModel(Integer maSPCT) {


        DefaultTableModel defaultTableModel = new DefaultTableModel();

        List<Field> fields = Arrays.asList(LSGViewModel.class.getDeclaredFields())
                .stream()
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .collect(Collectors.toList());


        defaultTableModel.setColumnCount(0);
        for (Field f : fields) {
            defaultTableModel.addColumn(f.getAnnotation(DataHeader.class).name());
        }

        defaultTableModel.setRowCount(0);

        List<String> rowData = new ArrayList<>();

        for (LSGViewModel gia : this.getLSGViewModels(maSPCT)) {
            rowData = new ArrayList<>();
            try {
                for (Field j : fields) {
                    j.setAccessible(true);

                    if (j.get(gia) == null) {
                        rowData.add("");
                        continue;
                    }

                    if (j.getType().equals(Date.class)) {
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
