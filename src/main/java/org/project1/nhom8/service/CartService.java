package org.project1.nhom8.service;

import org.project1.nhom8.dto.Cart;
import org.project1.nhom8.dto.CartDetail;
import org.project1.nhom8.exception.CustomerPhoneNumberExistedException;
import org.project1.nhom8.repository.GiaRepository;
import org.project1.nhom8.repository.SPCTRepository;
import org.project1.nhom8.repository.VoucherRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private Map<String, Cart> carts;

    private final HoaDonService hoaDonService;

    private final VoucherRepository voucherRepository;

    private final SPCTRepository spctRepository;

    private final KhuyenMaiService khuyenMaiService;

    private final GiaRepository giaRepository;

    public CartService() {

        this.carts = new HashMap<>();

        this.hoaDonService = new HoaDonService();

        this.voucherRepository = new VoucherRepository();

        this.spctRepository = new SPCTRepository();

        this.khuyenMaiService = new KhuyenMaiService();

        this.giaRepository = new GiaRepository();
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


    public void refresh() {

        for (Cart cart : this.getCartsAsList()) {
            refreshOne(cart);
        }

    }

    public void refreshOne(Cart cart) {

        String couponId = null;

        for (CartDetail cd : cart.getProducts().values()) {

            couponId = spctRepository.getIdKM(cd.getProduct().getMaSPCT());

            System.out.println(couponId);

            if (couponId != null) {
                cd.setCoupon(khuyenMaiService.findById(couponId));
                System.out.println(cd.getCoupon().getGia());
            } else {
                cd.setCoupon(null);
            }

            cd.setPrice(giaRepository.getgiaMoiNhat(cd.getProduct().getMaSPCT()));
        }
    }
}
