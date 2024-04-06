package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

public class CartDetail {

    @DataHeader(name = "mã sản phẩm")
    private Integer productId;

    @DataHeader(name = "tên sản phẩm")
    private String productName;

    @DataHeader(name = "giá")
    private Double price;

    @DataHeader(name = "màu sắc")
    private String colorName;

    @DataHeader(name = "size")
    private String sizeName;

    @DataHeader(name = "số lượng")
    private Integer quantity;

    public CartDetail() {
    }

    public CartDetail(Integer quantity, StoreProductViewModel productView) {
        this.quantity = quantity;

        this.productId = productView.getId();
        this.productName = productView.getName();
        this.colorName = productView.getColor();
        this.sizeName = productView.getSize();
        this.price = productView.getPrice();
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getColorName() {
        return colorName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}





