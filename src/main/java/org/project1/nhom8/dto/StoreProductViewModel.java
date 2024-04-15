package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

public class StoreProductViewModel {

    @DataHeader(name = "mã sản phẩm")
    private Integer id;

    @DataHeader(name = "tên sản phẩm")
    private String name;

    @DataHeader(name = "màu sắc")
    private String color;

    @DataHeader(name = "size")
    private String size;

    @DataHeader(name = "số lượng")
    private Integer quantity;

    @DataHeader(name = "giá gốc")
    private Double price;

    @DataHeader(name = "giá bán")
    private Double sellingPrice;

    public StoreProductViewModel() {
    }

    public StoreProductViewModel(Integer id, String name, String color, String size, Integer quantity, Double price, Double sellingPrice) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.sellingPrice = sellingPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
