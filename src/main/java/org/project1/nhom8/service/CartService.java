package org.project1.nhom8.service;

import org.project1.nhom8.dto.Cart;
import org.project1.nhom8.exception.CustomerPhoneNumberExistedException;

import java.util.List;
import java.util.Map;

public class CartService {

    private Map<String, Cart> carts;

    private final HoaDonService hoaDonService;

    public CartService() {
        this.hoaDonService = new HoaDonService();
    }

    /**
     * @param cart
     * @return cart with invoiceId if
     * {@link CartService#carts} not contain {@link Cart#getCustomerPhoneNumber()}
     */
    public Cart add(Cart cart) throws CustomerPhoneNumberExistedException {

        if (carts.get(cart.getCustomerPhoneNumber()) == null) {
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

    public List<Cart> toList() {
        return carts.values().stream().toList();
    }
}
