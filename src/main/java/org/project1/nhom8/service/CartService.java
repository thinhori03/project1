package org.project1.nhom8.service;

import org.project1.nhom8.dto.Cart;
import org.project1.nhom8.dto.CartDetail;
import org.project1.nhom8.exception.CustomerPhoneNumberExistedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private Map<String, Cart> carts;

    private final HoaDonService hoaDonService;

    public CartService() {

        this.carts = new HashMap<>();

        this.hoaDonService = new HoaDonService();
    }

    /**
     * @param cart
     * @return cart with invoiceId if
     * {@link CartService#carts} not contain {@link Cart#getCustomerPhoneNumber()}
     */
    public Cart add(Cart cart) throws CustomerPhoneNumberExistedException {

        if (carts.get(cart.getCustomerPhoneNumber()) != null) {
            throw new CustomerPhoneNumberExistedException(cart
                    .getCustomerPhoneNumber());
        }

        String cartId = this.hoaDonService.taoMaHoaDon();
        cart.setInvoiceId(cartId);

        carts.put(cartId, cart);

        return cart;
    }

    public boolean remove(String cartId) {
        return this.carts.remove(cartId) != null;
    }

    public Map<String, Cart> getCarts() {
        return carts;
    }

    public HoaDonService getHoaDonService() {
        return this.hoaDonService;
    }

    public List<Cart> toList() {
        return carts.values().stream().toList();
    }

    public List<Cart> getCartsAsList() {
        return carts.values().stream().toList();
    }


    public Double getTotalPrice(String cartId) {

        Double result = Double.valueOf(0);

        Cart cart = this.carts.get(cartId);

        if (cart == null) {
            return Double.valueOf(0);
        }

        for (CartDetail cartDetail : cart.getProducts().values().stream().toList()) {
            result = result + cartDetail.getPrice().getGia();

            if (cartDetail.getCoupon() != null) {
                result -= cartDetail.getCoupon().getGia();
            }
        }

        return result;
    }

    /**
     * @param cart current cart
     * @return total price
     */
    public Double getTotalPrice(Cart cart) {
        Double totalPrice = Double.valueOf(0);
        for (CartDetail cd : cart.getProducts().values()) {
            totalPrice += (cd.getQuantity() * cd.getPrice().getGia());

            if (cd.getCoupon() != null) {
                totalPrice -= (cd.getCoupon().getGia() * cd.getQuantity());
            }
        }

        return totalPrice;
    }
}
