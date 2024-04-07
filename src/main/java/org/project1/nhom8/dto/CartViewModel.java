package org.project1.nhom8.dto;

import org.project1.nhom8.util.data.visual.DataHeader;

import java.util.Date;

public class CartViewModel {

    @DataHeader(name = "mã hóa đơn")
    private String invoiceId;

    @DataHeader(name = "tên khách hàng")
    private String customerName;

    @DataHeader(name = "số diện thoại khách hàng")
    private String customerPhoneNumber;

    @DataHeader(name = "ngày tạo")
    private Date creationDate;

    @DataHeader(name = "voucher")
    private Double voucherPrice;


    public CartViewModel() {
    }

    public CartViewModel(String invoiceId, String customerName, String customerPhoneNumber, Date creationDate, Double voucherPrice) {
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.creationDate = creationDate;
        this.voucherPrice = voucherPrice;
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

    public Double getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(Double voucherPrice) {
        this.voucherPrice = voucherPrice;
    }
}
