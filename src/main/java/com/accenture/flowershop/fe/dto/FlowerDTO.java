package com.accenture.flowershop.fe.dto;

import org.dozer.Mapping;

import java.math.BigDecimal;

public class FlowerDTO {
    private Long idFlower;
    private String nameFlower;
    private BigDecimal price;
    private Integer qtyStock;

    public FlowerDTO(Long idFlower, String nameFlower, BigDecimal price, Integer qtyStock) {
        this.idFlower = idFlower;
        this.nameFlower = nameFlower;
        this.price = price;
        this.qtyStock = qtyStock;
    }

    public FlowerDTO() {
    }

    public Integer getQtyStock() {
        return qtyStock;
    }

    public void setQtyStock(Integer qtyStock) {
        this.qtyStock = qtyStock;
    }

    @Mapping("id")
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
