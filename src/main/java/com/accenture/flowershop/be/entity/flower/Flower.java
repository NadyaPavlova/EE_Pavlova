package com.accenture.flowershop.be.entity.flower;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Flowers")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idFlower")
    @SequenceGenerator(name="idFlower", sequenceName = "seq_flower")
    @Column(name="idFlower")
    private Long id;

    @Column(name = "nameFlower")
    private String nameFlower;

    @Column (name = "price")
    private BigDecimal price;

    @Column (name = "qtyStock")
    private Integer qtyStock;

    public Flower() {
    }

    public Flower(Long idFlower, String nameFlower, BigDecimal price, Integer qtyStock) {
        this.id = idFlower;
        this.nameFlower = nameFlower;
        this.price = price;
        this.qtyStock = qtyStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getQtyStock() {
        return qtyStock;
    }

    public void setQtyStock(Integer qty) {
        this.qtyStock = qty;
    }

    public void reduceQtyStock(int qty){
        this.qtyStock = this.qtyStock - qty;
    }

}

