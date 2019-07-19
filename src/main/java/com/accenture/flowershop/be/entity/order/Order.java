package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.orderItem.OrderItem;
import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity(name = "ORDER")
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "idOrder")
    @SequenceGenerator(name="idOrder", sequenceName = "seq_order")
    @Column(name="idOrder")
    private Long idOrder;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Column
    private BigDecimal priceSum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List <OrderItem> itemList;

    public Order() {
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPrce_sum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }
}
