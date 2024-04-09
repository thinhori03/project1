package org.project1.nhom8.util;

import org.project1.nhom8.dto.Cart;
import org.project1.nhom8.dto.CartDetail;
import org.project1.nhom8.repository.VoucherRepository;

public class CartUtil {

    private static final VoucherRepository voucherRepository = new VoucherRepository();

    public static Double getTotalPrice(Cart cart) {

        Double totalPrice = Double.valueOf(0);

        for (CartDetail cd : cart.getProducts().values()) {
            totalPrice += (cd.getQuantity() * cd.getPrice().getGia());

            if (cd.getCoupon() != null) {
                totalPrice -= (cd.getCoupon().getGia() * cd.getQuantity());
            }
        }

        return totalPrice;
    }

    public static Double getFinalPrice(Cart cart) {

        Double finalPrice = getTotalPrice(cart);

        if (cart.getVoucherId() != null) {
            finalPrice -= voucherRepository.findById(cart.getVoucherId()).getGiaTri();
        }

        return finalPrice;
    }
}
