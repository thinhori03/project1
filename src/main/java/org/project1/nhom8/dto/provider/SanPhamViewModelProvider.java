package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.SanPhamViewModel;
import org.project1.nhom8.model.SanPhamModel;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.util.data.visual.DataHeader;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SanPhamViewModelProvider {

    private final SanPhamRepository sanPhamRepository;

    public SanPhamViewModelProvider() {
        this.sanPhamRepository = new SanPhamRepository();
    }

    public List<SanPhamViewModel> getSanPhamViewModel() {
        List<SanPhamViewModel> sanPhamViewModels = new ArrayList<>();

        List<SanPhamModel> sanPhamModels = sanPhamRepository.findAll();

        for (SanPhamModel spm : sanPhamModels) {
            sanPhamViewModels.add(SanPhamViewModel.builder()
                    .maSanPham(spm.getMasp())
                    .tenSanPham(spm.getTensp())
                    .build());
        }
        return sanPhamViewModels;
    }

    public  DefaultTableModel toTableModel() {

        DefaultTableModel defaultTableModel =new DefaultTableModel();

        List<Field> fields = Arrays.asList(SanPhamViewModel.class.getDeclaredFields())
                .stream()
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .collect(Collectors.toList());


        defaultTableModel.setColumnCount(0);

        for (Field f : fields) {
            defaultTableModel.addColumn(f.getAnnotation(DataHeader.class).name());
        }

        defaultTableModel.setRowCount(0);

        List<String> rowData = new ArrayList<>();

        for (SanPhamViewModel spvm : this.getSanPhamViewModel()) {
            rowData = new ArrayList<>();
            try {
                for (Field j : fields) {
                    j.setAccessible(true);
                    rowData.add(j.get(spvm).toString());
                    System.out.println(j.get(spvm).toString());
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

            defaultTableModel.addRow(rowData.toArray());
        }

        return defaultTableModel;
    }
}
