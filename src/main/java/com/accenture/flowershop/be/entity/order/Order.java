package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.order_item.Order_item;
import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Entity(name = "ORDER")
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_order")
    @SequenceGenerator(name="id_order", sequenceName = "seq_order")
    @Column(name="id_order")
    private Long id_order;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column
    private BigDecimal price_sum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List <Order_item> itemList;

    public Order() {
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPrice_sum() {
        return price_sum;
    }

    public void setPrce_sum(BigDecimal price_sum) {
        this.price_sum = price_sum;
    }

    public List<Order_item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Order_item> itemList) {
        this.itemList = itemList;
    }
}
