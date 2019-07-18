package com.accenture.flowershop.be.entity.flower;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity(name = "Flower")
@Table(name = "Flowers")
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_flower")
    @SequenceGenerator(name="id_flower", sequenceName = "seq_flower")
    @Column(name="id_flower")
    private Long id_flower;

    @Column(name = "NAME_FLOWER")
    private String name_flower;

    @Column (name = "PRICE")
    private BigDecimal price;

    @Column (name = "QTY_STOCK")
    private Integer qty_stock;

    public Flower() {
    }

    public Flower(Long id_flower, String name_flower, BigDecimal price, Integer qty) {
        this.id_flower = id_flower;
        this.name_flower = name_flower;
        this.price = price;
        this.qty_stock = qty;
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

    public Integer getQty_stock() {
        return qty_stock;
    }

    public void setQty_stock(Integer qty) {
        this.qty_stock = qty;
    }


}

