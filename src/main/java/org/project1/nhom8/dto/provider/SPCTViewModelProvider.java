package org.project1.nhom8.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.model.SanPhamModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.MauSacRepository;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.repository.SizeRepository;
import org.project1.nhom8.util.data.visual.DataHeader;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Setter
@Getter
@Builder
public class SPCTViewModelProvider {

    private SanPhamRepository sanPhamRepository;

    private SPCTRepository spctRespository;

    private GiaRepository giaRepository;

    private SizeRepository sizeRepository;

    private MauSacRepository mauSacRepository;

    public SPCTViewModelProvider() {

        this.sanPhamRepository = new SanPhamRepository();

        this.spctRespository = new SPCTRepository();

        this.giaRepository = new GiaRepository();

        this.sizeRepository = new SizeRepository();

        this.mauSacRepository = new MauSacRepository();
    }

    public List<SPCTViewModel> SPCTViewModel() {
        List<SPCTViewModel> result = new ArrayList<>();

        SPCTViewModel spctViewModel = null;

        List<SPCTModel> spctModels = spctRespository
                .findAll();

        SanPhamModel sp = new SanPhamModel();
        GiaModel gia = new GiaModel();

        for (SPCTModel spctModel : spctModels) {
            new SPCTViewModel();

            sp = sanPhamRepository.findById(spctModel.getMaSPCT());
            gia = giaRepository.getgiaMoiNhat(spctModel.getMaSPCT());


            result.add(SPCTViewModel.builder()
                    .maSPCT(spctModel.getMaSPCT())
                    .maSP(spctModel.getMaSP())
                    .tenSP(sp.getTensp())
                    .gia(giaRepository.getgiaMoiNhat(spctModel.getMaSPCT()).getGia())
                    .size(sizeRepository.findById(spctModel.getMasize()).getTensize())
                    .mauSac(mauSacRepository.findById(spctModel.getMaMauSac()).getTenmau())
                    .trangThai(sp.getTrangThai())
                    .soLuong(spctModel.getSoluong())
                    .build());
        }
        return result;
    }

    public TableModel toTableModel() {

        DefaultTableModel defaultTableModel =new DefaultTableModel();

        List<Field> fields = Arrays.asList(SPCTViewModel.class.getDeclaredFields())
                .stream()
                .filter(o -> o.isAnnotationPresent(DataHeader.class))
                .collect(Collectors.toList());


        defaultTableModel.setColumnCount(0);
        for (Field f : fields) {
            defaultTableModel.addColumn(f.getAnnotation(DataHeader.class).name());
        }

        defaultTableModel.setRowCount(0);

        List<String> rowData = new ArrayList<>();

        for (SPCTViewModel spctViewModel : this.SPCTViewModel()) {
            rowData = new ArrayList<>();
            try {
                for (Field j : fields) {
                    j.setAccessible(true);
                    rowData.add(j.get(spctViewModel).toString());
                    System.out.println(j.get(spctViewModel).toString());
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

            defaultTableModel.addRow(rowData.toArray());
        }

        return defaultTableModel;
    }
}
