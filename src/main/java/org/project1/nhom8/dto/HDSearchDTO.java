package org.project1.nhom8.dto;

import java.util.Date;

public class HDSearchDTO {

    private Date beginCreationDate;
    private Date endCreationDate;

    private Date beginPaymenetDate;
    private Date endPaymentDate;
    
    public HDSearchDTO() {
        this.beginCreationDate = null;
        this.endCreationDate = null;
        this.beginPaymenetDate = null;
        this.endPaymentDate = null;
    }

    public HDSearchDTO(Date beginCreationDate, Date endCreationDate, Date beginPaymenetDate, Date endPaymentDate) {
        this.beginCreationDate = beginCreationDate;
        this.endCreationDate = endCreationDate;
        this.beginPaymenetDate = beginPaymenetDate;
        this.endPaymentDate = endPaymentDate;
    }

    public Date getBeginCreationDate() {
        return beginCreationDate;
    }

    public void setBeginCreationDate(Date beginCreationDate) {
        this.beginCreationDate = beginCreationDate;
    }

    public Date getEndCreationDate() {
        return endCreationDate;
    }

    public void setEndCreationDate(Date endCreationDate) {
        this.endCreationDate = endCreationDate;
    }

    public Date getBeginPaymenetDate() {
        return beginPaymenetDate;
    }

    public void setBeginPaymenetDate(Date beginPaymenetDate) {
        this.beginPaymenetDate = beginPaymenetDate;
    }

    public Date getEndPaymentDate() {
        return endPaymentDate;
    }

    public void setEndPaymentDate(Date endPaymentDate) {
        this.endPaymentDate = endPaymentDate;
    }
}
