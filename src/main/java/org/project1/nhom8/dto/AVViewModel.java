package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

public class AVViewModel {

    @DataHeader(name = "tổng tiền")
    private Double condPrice;

    @DataHeader(name = "giảm")
    private Double price;

    public AVViewModel(Double condPrice, Double price) {
        this.condPrice = condPrice;
        this.price = price;
    }

    public Double getCondPrice() {
        return condPrice;
    }

    public void setCondPrice(Double condPrice) {
        this.condPrice = condPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
