package com.accenture.flowershop.be.entity.order_item;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;

import javax.persistence.*;
@Entity(name = "ORDER_ITEM")
@Table(name = "ORDER_ITEM")
public class Order_item {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_order_it")
    @SequenceGenerator(name="id_order_it", sequenceName = "seq_order_item")
    private Long id_order_it;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
    private Order order;

    @OneToOne
    @JoinColumn(name = "id_flower")
    private Flower flower;

    @Column
    private Integer qty_flower;


    public Long getId_order_it() {
        return id_order_it;
    }

    public void setId_order_it(Long id_order_it) {
        this.id_order_it = id_order_it;
    }

    public Order_item() {
    }

    public Flower getFlower() {
        return flower;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Integer getQty_flower() {
        return qty_flower;
    }

    public void setQty_flower(Integer qty_flower) {
        this.qty_flower = qty_flower;
    }
}

