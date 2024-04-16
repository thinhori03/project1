package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

public class ProductExport {

    @DataHeader(name = "mã sản phẩm")
    private String id;

    @DataHeader(name = "tên sản phẩm")
    private String name;

    @DataHeader(name = "màu màu sắc")
    private String color;

    @DataHeader(name = "size")
    private String size;

    @DataHeader(name = "số luợng")
    private Integer quantity;

    @DataHeader(name = "giá gốc")
    private Integer price;

    @DataHeader(name = "giá bán")
    private Integer sellingPrice;

    public ProductExport() {

    }

    public ProductExport(String id, String name, String color, String size, Integer quantity, Integer price, Integer sellingPrice) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.sellingPrice = sellingPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
