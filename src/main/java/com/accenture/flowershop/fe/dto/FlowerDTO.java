package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class FlowerDTO {
    private Long idFlower;
    private String nameFlower;
    private BigDecimal price;
    private Integer qtyStock;

    @Autowired
    private FlowerBusinessService fbs;


    public FlowerDTO() {
    }

    public Integer getQtyStock() {
        return qtyStock;
    }

    public void setQtyStock(Integer qtyStock) {
        this.qtyStock = qtyStock;
    }

    public Flower getFlowerById(Long id){
        return fbs.getFlowerById(id);
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
