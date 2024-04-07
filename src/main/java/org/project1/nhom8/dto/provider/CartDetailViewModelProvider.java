package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.CartDetail;
import org.project1.nhom8.dto.CartDetailViewModel;
import org.project1.nhom8.model.SanPhamModel;
import org.project1.nhom8.repository.MauSacRepository;
import org.project1.nhom8.repository.SanPhamRepository;
import org.project1.nhom8.repository.SizeRepository;

import java.util.ArrayList;
import java.util.List;

public class CartDetailViewModelProvider {

    private final SanPhamRepository sanPhamRepository;

    private final MauSacRepository mauSacRepository;

    private final SizeRepository sizeRepository;

    public CartDetailViewModelProvider() {
        sanPhamRepository = new SanPhamRepository();
        mauSacRepository = new MauSacRepository();
        sizeRepository = new SizeRepository();
    }

    public List<CartDetailViewModel> gitModels(List<CartDetail> models) {

        List<CartDetailViewModel> result = new ArrayList<>();

        CartDetailViewModel cdvm = null;

        SanPhamModel spm = null;

        for (CartDetail model : models) {

            spm = sanPhamRepository.findById(model.getProduct().getMaSP());

            cdvm = new CartDetailViewModel();

            cdvm.setProductId(model.getProduct().getMaSPCT());
            cdvm.setProductName(spm.getTensp());
            cdvm.setPrice(model.getPrice().getGia());
            cdvm.setQuantity(model.getQuantity());
            cdvm.setColorName(mauSacRepository.findById(model
                    .getProduct().getMaMauSac()).getTenmau());
            cdvm.setSize(sizeRepository.findById(model.getProduct()
                    .getMasize()).getTensize());

            if (model.getCoupon() == null) {
                cdvm.setCouponPrice(Double.valueOf(0));
            } else {

            }


        }

        return result;
    }
}
