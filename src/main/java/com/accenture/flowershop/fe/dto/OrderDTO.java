package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.business.StatusOrders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long idOrder;

    private UserDTO userDTO;

    private List<OrderItemDTO> basketList = new ArrayList<>();

    private StatusOrders status;

    private LocalDate creationDate;

    private LocalDate closingDate;

    private BigDecimal priceSum;


    public OrderDTO() {
    }
    public Long getIdOrder() { return idOrder; }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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

    public StatusOrders getStatus() {
        return status;
    }

    public void setStatus(StatusOrders status) {
        this.status = status;
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

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }


}
