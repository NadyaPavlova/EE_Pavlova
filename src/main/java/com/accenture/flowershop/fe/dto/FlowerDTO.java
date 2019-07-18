package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;

public class FlowerDTO {
    private Long id_flower;
    private String name_flower;
    private BigDecimal price;

    public FlowerDTO(Long id_flower, String name_flower, BigDecimal price) {
        this.id_flower = id_flower;
        this.name_flower = name_flower;
        this.price = price;
    }

    public FlowerDTO(Flower flower) {
        this.id_flower = flower.getId_flower();
        this.name_flower = flower.getName_flower();
        this.price = flower.getPrice();
    }
    public Long getId_flower() {
        return id_flower;
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

}
