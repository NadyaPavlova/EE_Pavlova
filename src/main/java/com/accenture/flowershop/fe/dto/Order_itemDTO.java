package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class Order_itemDTO {
    private Long id_flower;
    private String name_flower;
    private BigDecimal price;
    private int qty_fl;

    public Long getId_flower() {
        return id_flower;
    }

    public Order_itemDTO(Long id_flower, String name_flower, BigDecimal price) {
        this.id_flower = id_flower;
        this.name_flower = name_flower;
        this.price = price;
        this.qty_fl = 1;
    }

    public void setId_flower(Long id_flower) {
        this.id_flower = id_flower;
    }

    public String getName_flower() {
        return name_flower;
    }

    public void setName_flower(String name_flower) {
        this.name_flower = name_flower;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQty_fl() {
        return qty_fl;
    }

    public void setQty_fl(int qty_fl) {

        this.qty_fl = qty_fl;
    }
}
