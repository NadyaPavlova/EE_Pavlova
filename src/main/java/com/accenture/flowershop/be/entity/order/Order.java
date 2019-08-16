package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.business.StatusOrders;
import com.accenture.flowershop.be.entity.orderItem.OrderItem;
import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "idOrder")
    @SequenceGenerator(name="idOrder", sequenceName = "seq_order", allocationSize = 1, initialValue = 1)
    @Column(name="idOrder")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    private BigDecimal priceSum;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List <OrderItem> itemList;

    private LocalDate creationDate;

    private LocalDate closingDate;

    @Enumerated(EnumType.STRING)
    private StatusOrders status;



    public Order() {
    }

    public void close() {
        this.setStatus(StatusOrders.CLOSED);
        this.setClosingDate(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public StatusOrders getStatus() {
        return status;
    }

    public void setStatus(StatusOrders status) {
        this.status = status;
    }

}
