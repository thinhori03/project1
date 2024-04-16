package org.project1.nhom8.dto;

import org.project1.nhom8.model.Login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private String invoiceId;

    private String customerName;

    // id customer exists in DB
    private String customerPhoneNumber;

    private Date creationDate;

    private String voucherId;


    private Double payment;

    private Login creator;

    private Login saver;

    private String paymentMethod;

    private Map<Integer, CartDetail> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public Cart(String customerName, String customerPhoneNumber) {

        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;

        this.products = new HashMap<>();
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

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Login getCreator() {
        return creator;
    }

    public void setCreator(Login creator) {
        this.creator = creator;
    }

    public Login getSaver() {
        return saver;
    }

    public void setSaver(Login saver) {
        this.saver = saver;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Map<Integer, CartDetail> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, CartDetail> products) {
        this.products = products;
    }
}
