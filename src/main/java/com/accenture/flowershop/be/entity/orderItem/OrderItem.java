package com.accenture.flowershop.be.entity.orderItem;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;

import javax.persistence.*;
@Entity(name = "OrderItem")
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "idOrderIt")
    @SequenceGenerator(name="idOrderIt", sequenceName = "seq_order_item")
    private Long idOrderIt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idOrder")
    private Order order;

    @OneToOne
    @JoinColumn(name = "idFlower")
    private Flower flower;

    @Column
    private Integer qtyFlower;

    public OrderItem() {
    }

    public Long getIdOrderIt() {
        return idOrderIt;
    }

    public void setIdOrderIt(Long idOrderIt) {
        this.idOrderIt = idOrderIt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Integer getQtyFlower() {
        return qtyFlower;
    }

    public void setQtyFlower(Integer qtyFlower) {
        this.qtyFlower = qtyFlower;
    }
}

