package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Long id;

    private UserDTO userDTO;

    private List<Order_itemDTO> orderFlowersList;

    private String status;

    private Integer discount;

    private BigDecimal finalPrice;

    OrderDTO(){}

    public OrderDTO(Long id, UserDTO userDTO, List<Order_itemDTO> orderFlowersList, String status, Integer discount, BigDecimal finalPrice) {
        this.id = id;
        this.userDTO = userDTO;
        this.orderFlowersList = orderFlowersList;
        this.status = status;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<Order_itemDTO> getOrderFlowersList() {
        return orderFlowersList;
    }

    public void setOrderFlowersList(List<Order_itemDTO> orderFlowersList) {
        this.orderFlowersList = orderFlowersList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
}
