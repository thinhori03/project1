package org.project1.nhom8.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Cart {

    private String invoiceId;

    private String customerName;

    // id customer exists in DB
    private String customerPhoneNumber;

    private Date creationDate;

    private String voucherId;

    private Set<CartDetail> products;

    public Cart() {
        this.products = new HashSet<>();
    }

    public Cart(String customerName, String customerPhoneNumber) {

        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;

        this.products = new HashSet<>();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public Set<CartDetail> getProducts() {
        return products;
    }

    public void setProducts(Set<CartDetail> products) {
        this.products = products;
    }
}
