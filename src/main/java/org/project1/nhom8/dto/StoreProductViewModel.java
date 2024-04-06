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

    @DataHeader(name = "giá")
    private Double price;

    @DataHeader(name = "số lượng")
    private Integer quantity;

    public StoreProductViewModel() {
    }

    public StoreProductViewModel(Integer id, String name, String color, String size, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
