package org.project1.nhom8.dto;

import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.KhuyenMai;
import org.project1.nhom8.model.SPCTModel;

import java.util.Objects;

public class CartDetail {

    private SPCTModel product;

    private GiaModel price;

    private KhuyenMai coupon;

    private Integer quantity;

    public CartDetail() {
    }

    public CartDetail(Integer quantity) {
        this.quantity = quantity;
    }

    public SPCTModel getProduct() {
        return product;
    }

    public void setProduct(SPCTModel product) {
        this.product = product;
    }

    public GiaModel getPrice() {
        return price;
    }

    public void setPrice(GiaModel price) {
        this.price = price;
    }

    public KhuyenMai getCoupon() {
        return coupon;
    }

    public void setCoupon(KhuyenMai coupon) {
        this.coupon = coupon;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        CartDetail compareO = (CartDetail) o;
        return compareO.product.getMaSPCT() == this.getProduct().getMaSPCT();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.product.getMaSPCT());
    }
}





