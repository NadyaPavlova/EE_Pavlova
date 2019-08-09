package com.accenture.flowershop.fe.dto;

import com.accenture.flowershop.be.business.StatusOrders;
import org.dozer.Mapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class OrderDTO {

    private Long idOrder;

    private UserDTO userDTO;

    private List<OrderItemDTO> itemList = new ArrayList<>();

    private StatusOrders status;

    private String creationDate;

    private String closingDate;

    private BigDecimal priceSum;


    public OrderDTO() {

    }
    public Long getIdOrder() { return idOrder; }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    @Mapping("user")
    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<OrderItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDTO> itemList) {
        this.itemList = itemList;
    }

    public OrderDTO(List<OrderItemDTO> itemList) {
        this.itemList = itemList;
    }

    public StatusOrders getStatus() {
        return status;
    }

    public void setStatus(StatusOrders status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String  creationDate) {
        this.creationDate = creationDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String  closingDate) {
        this.closingDate = closingDate;
    }

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }


}
