package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long idOrder;

    private List<OrderItemDTO> basketList = new ArrayList<>();

    private  String status;


    private Date creationDate;

    private Date closingDate;

    private BigDecimal priceSum;


    public OrderDTO() {
    }
    public Long getIdOrder() { return idOrder; }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public List<OrderItemDTO> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<OrderItemDTO> basketList) {
        this.basketList = basketList;
    }

    public OrderDTO(List<OrderItemDTO> basketList) {
        this.basketList = basketList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }


}
