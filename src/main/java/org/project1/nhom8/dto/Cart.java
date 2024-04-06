package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {
    @DataHeader(name = "tên khách hàng")
    private String customerName;

    // id customer exists in DB
    @DataHeader(name = "sô diện thoại")
    private String customerPhoneNumber;

    @DataHeader(name = "thời gian tạo")
    private Date creationDate;

    private List<CartDetail> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public Cart(String customerName, String customerPhoneNumber) {

        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;

        this.products = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<CartDetail> getProducts() {
        return products;
    }
}
