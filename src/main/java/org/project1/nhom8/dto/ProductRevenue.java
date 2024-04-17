package org.project1.nhom8.dto;

public class ProductRevenue {

    private String name;

    private Double revenue;

    public ProductRevenue(String name, Double revenue) {
        this.name = name;
        this.revenue = revenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}
