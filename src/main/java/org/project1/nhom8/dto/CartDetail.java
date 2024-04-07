package org.project1.nhom8.dto;

import org.project1.nhom8.model.GiaModel;
import org.project1.nhom8.model.KhuyenMai;
import org.project1.nhom8.model.SPCTModel;

public class CartDetail {
    
    private SPCTModel product;

    private GiaModel price;

    private KhuyenMai coupon;

    private Integer quantity;

    public CartDetail() {
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

    //    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof CartDetail)) {
//            return false;
//        }
//
//        CartDetail compareO = (CartDetail) o;
//        return compareO.productId.equals(this.productId);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(productId);
//    }
}





