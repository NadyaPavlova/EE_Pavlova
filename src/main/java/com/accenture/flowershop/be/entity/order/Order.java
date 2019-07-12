package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.basket.Basket;
import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_order;

    @JoinColumn(name = "user")
    private User user;

    @Column
    private BigDecimal prce_sum;

    @Column
    private BigDecimal price_sum;

    @OneToMany(mappedBy = "id_order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Basket> basket;

    public Order() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
