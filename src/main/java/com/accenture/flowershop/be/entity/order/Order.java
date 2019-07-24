package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.orderItem.OrderItem;
import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "idOrder")
    @SequenceGenerator(name="idOrder", sequenceName = "seq_order", allocationSize = 1, initialValue = 1)
    @Column(name="idOrder")
    private Long idOrder;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    @Column
    private BigDecimal priceSum;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List <OrderItem> itemList;

    @Column
    private Date creationDate;

    @Column
    private Date closingDate;

    @Column
    private String status;


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

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
