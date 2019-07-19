package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
    private Long idFlower;
    private String nameFlower;
    private BigDecimal priceFlower;
    private int qtyFlower;

    public OrderItemDTO() {
    }

    public Long getIdFlower() {
        return idFlower;
    }

    public void setIdFlower(Long idFlower) {
        this.idFlower = idFlower;
    }

    public String getNameFlower() {
        return nameFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    public BigDecimal getPriceFlower() {
        return priceFlower;
    }

    public void setPriceFlower(BigDecimal priceFlower) {
        this.priceFlower = priceFlower;
    }

    public int getQtyFlower() {
        return qtyFlower;
    }

    public void setQtyFlower(int qtyFlower) {
        this.qtyFlower = qtyFlower;
    }
}
