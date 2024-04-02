package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.SPCTViewModel;
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
import java.util.stream.Collectors;


public class SPCTViewModelProvider {

    private final SanPhamRepository sanPhamRepository;

    private final SPCTRepository spctRespository;

    private final GiaRepository giaRepository;

    private final SizeRepository sizeRepository;

    private final MauSacRepository mauSacRepository;

    public SPCTViewModelProvider() {

        this.sanPhamRepository = new SanPhamRepository();

        this.spctRespository = new SPCTRepository();

        this.giaRepository = new GiaRepository();

        this.sizeRepository = new SizeRepository();

        this.mauSacRepository = new MauSacRepository();
    }

    public List<SPCTViewModel> SPCTViewModel() {
        return getViewModels(spctRespository.findAll());
    }

    public TableModel toTableModel(List<SPCTViewModel> models) {

        DefaultTableModel defaultTableModel = new DefaultTableModel();

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

        for (SPCTViewModel spctViewModel : models) {
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


    public List<SPCTViewModel> getViewModels(List<SPCTModel> spctModels) {
        List<SPCTViewModel> result = new ArrayList<>();

        SPCTViewModel spctViewModel = null;

        SanPhamModel sp = new SanPhamModel();

        SPCTViewModel spctvm = null;

        for (SPCTModel spctModel : spctModels) {
            new SPCTViewModel();

            sp = sanPhamRepository.findById(spctModel.getMaSP());

            spctvm = new SPCTViewModel();

            spctvm.setMaSPCT(spctModel.getMaSPCT());
            spctvm.setMaSP(spctModel.getMaSP());
            spctvm.setTenSP(sp.getTensp());
            spctvm.setGia(giaRepository.getgiaMoiNhat(spctModel.getMaSPCT()).getGia());
            spctvm.setSize(sizeRepository.findById(spctModel.getMasize()).getTensize());
            spctvm.setMauSac(mauSacRepository.findById(spctModel.getMaMauSac()).getTenmau());
            spctvm.setTrangThai(sp.getTrangthai());
            spctvm.setSoLuong(spctModel.getSoluong());

            result.add(spctvm);
        }
        return result;
    }
}
