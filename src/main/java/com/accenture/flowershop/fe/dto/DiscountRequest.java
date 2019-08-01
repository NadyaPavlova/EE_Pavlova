package com.accenture.flowershop.fe.dto;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class DiscountRequest {

    private Long customerId;
    private int newDiscount;

    public Long getCustomerId() {
        return customerId;
    }

    @XmlElement
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getNewDiscount() {
        return newDiscount;
    }

    @XmlElement
    public void setNewDiscount(int newDiscount) {
        this.newDiscount = newDiscount;
    }

}
