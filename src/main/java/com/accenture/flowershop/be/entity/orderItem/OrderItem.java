package com.accenture.flowershop.be.entity.orderItem;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "OrderItem")
@Table(name = "ORDERITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "seq_order_item", allocationSize = 1)
    @Column(name = "idItemOrder")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idOrder")
    private Order order;

    @OneToOne
    @JoinColumn(name = "idFlower")
    private Flower flower;

    @Column(name = "qty")
    private Integer qtyFlower;

    @Column(name = "price")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderItem() {
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

