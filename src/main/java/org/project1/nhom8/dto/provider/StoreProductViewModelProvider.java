package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.SPCTViewModel;
import org.project1.nhom8.dto.StoreProductViewModel;
import org.project1.nhom8.model.SPCTModel;
import org.project1.nhom8.model.SanPhamModel;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.MauSacRepository;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.repository.SizeRepository;

import java.util.ArrayList;
import java.util.List;

public class StoreProductViewModelProvider {

    private final SPCTRepository spctRepository;

    private final SanPhamRepository sanPhamRepository;

    private final MauSacRepository mauSacRepository;

    private final SizeRepository sizeRepository;

    private final GiaRepository giaRepository;

    public StoreProductViewModelProvider() {

        spctRepository = new SPCTRepository();

        sanPhamRepository = new SanPhamRepository();

        mauSacRepository = new MauSacRepository();

        sizeRepository = new SizeRepository();

        giaRepository = new GiaRepository();

    }

    public List<StoreProductViewModel> getModel(List<SPCTModel> models) {
        List<StoreProductViewModel> result = new ArrayList<>();

        StoreProductViewModel productViewModel = null;

        SanPhamModel sp = new SanPhamModel();

        for (SPCTModel model1 : models) {
            new SPCTViewModel();

            sp = sanPhamRepository.findById(model1.getMaSP());

            productViewModel = new StoreProductViewModel();

            productViewModel.setId(model1.getMaSPCT());
            productViewModel.setName(sp.getTensp());
            productViewModel.setColor(mauSacRepository.findById(model1.getMaMauSac()).getTenmau());
            productViewModel.setSize(sizeRepository.findById(model1.getMasize()).getTensize());
            productViewModel.setPrice(giaRepository.getgiaMoiNhat(model1.getMaSPCT()).getGia());
            productViewModel.setQuantity(model1.getSoluong());

            result.add(productViewModel);
        }

        return result;
    }
    
    public List<StoreProductViewModel> getAll() {
        return this.getModel(this.spctRepository.findAll());
    }

}
