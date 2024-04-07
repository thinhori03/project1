package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

public class CartDetailViewModel {

    @DataHeader(name = "mã sản phẩm")
    private Integer productId;

    @DataHeader(name = "tên sản phẩm")
    private String productName;

    @DataHeader(name = "màu sắc")
    private String colorName;

    @DataHeader(name = "size")
    private String size;

    @DataHeader(name = "số lượng")
    private Integer quantity;

    @DataHeader(name = "giá")
    private Double price;

    @DataHeader(name = "khuyến mại")
    private Double couponPrice;

    public CartDetailViewModel() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Double couponPrice) {
        this.couponPrice = couponPrice;
    }
}
