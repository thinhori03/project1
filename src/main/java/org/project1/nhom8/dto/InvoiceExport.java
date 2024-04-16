package org.project1.nhom8.dto;

import org.project1.nhom8.model.KhachHangModel;
import org.project1.nhom8.model.NhanVien;
import org.project1.nhom8.model.VoucherModel;

import java.util.List;

public class InvoiceExport {

    private String id;

    private String paymentDate;

    private NhanVien creator;

    private NhanVien seller;

    private Double totalPrice;

    private Integer payment;

    private KhachHangModel customer;

    private VoucherModel voucherModel;

    private List<ProductExport> products;

    public InvoiceExport() {

    }

    public InvoiceExport(String id, String paymentDate, NhanVien creator, NhanVien seller, Double totalPrice, Integer payment, KhachHangModel customer, VoucherModel voucherModel, List<ProductExport> products) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.creator = creator;
        this.seller = seller;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.customer = customer;
        this.voucherModel = voucherModel;
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public NhanVien getCreator() {
        return creator;
    }

    public void setCreator(NhanVien creator) {
        this.creator = creator;
    }

    public NhanVien getSeller() {
        return seller;
    }

    public void setSeller(NhanVien seller) {
        this.seller = seller;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public KhachHangModel getCustomer() {
        return customer;
    }

    public void setCustomer(KhachHangModel customer) {
        this.customer = customer;
    }

    public VoucherModel getVoucherModel() {
        return voucherModel;
    }

    public void setVoucherModel(VoucherModel voucherModel) {
        this.voucherModel = voucherModel;
    }

    public List<ProductExport> getProducts() {
        return products;
    }

    public void setProducts(List<ProductExport> products) {
        this.products = products;
    }
}
