package org.project1.nhom8.dto;

public class RevenueByMonth {

    private Double Revenue;

    private Byte month;

    public RevenueByMonth() {
    }

    public RevenueByMonth(Double revenue, Byte month) {
        Revenue = revenue;
        this.month = month;
    }

    public Double getRevenue() {
        return Revenue;
    }

    public void setRevenue(Double revenue) {
        Revenue = revenue;
    }

    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }
}
