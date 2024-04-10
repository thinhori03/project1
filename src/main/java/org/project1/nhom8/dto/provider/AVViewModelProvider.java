package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.AVViewModel;
import org.project1.nhom8.model.VoucherModel;
import org.project1.nhom8.repository.VoucherRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * available voucher view model prodiver
 */
public class AVViewModelProvider {

    private final VoucherRepository voucherRepository;

    public AVViewModelProvider() {
        this.voucherRepository = new VoucherRepository();
    }

    public List<AVViewModel> getModel(List<VoucherModel> models) {
        List<AVViewModel> result = new ArrayList<>();

        for (VoucherModel model : models) {
            result.add(new AVViewModel(model.getDiauKien(), model.getGiaTri()));
        }

        return result;
    }
}
