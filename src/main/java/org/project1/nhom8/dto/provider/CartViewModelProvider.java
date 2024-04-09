package org.project1.nhom8.dto.provider;

import org.project1.nhom8.dto.Cart;
import org.project1.nhom8.dto.CartViewModel;
import org.project1.nhom8.repository.VoucherRepository;
import org.project1.nhom8.util.CartUtil;

import java.util.ArrayList;
import java.util.List;

public class CartViewModelProvider {

    private final VoucherRepository voucherRepository;

    public CartViewModelProvider() {
        this.voucherRepository = new VoucherRepository();
    }

    public List<CartViewModel> getModel(List<Cart> models) {

        List<CartViewModel> result = new ArrayList<>();

        CartViewModel cvm = null;

        Double totalPrice = Double.valueOf(0);

        for (Cart model : models) {
            cvm = new CartViewModel();

            cvm.setInvoiceId(model.getInvoiceId());
            cvm.setCustomerName(model.getCustomerName());
            cvm.setCustomerPhoneNumber(model.getCustomerPhoneNumber());
            cvm.setCreationDate(model.getCreationDate());

            cvm.setTotalPrice(CartUtil.getTotalPrice(model));

            if (model.getVoucherId() != null) {
                cvm.setVoucherPrice(voucherRepository.findById(model.getVoucherId())
                        .getGiaTri());
            } else {
                cvm.setVoucherPrice(Double.valueOf(0));
            }
            result.add(cvm);
        }

        return result;
    }
}
