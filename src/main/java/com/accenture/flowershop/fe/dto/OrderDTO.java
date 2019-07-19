package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Long id;

    private UserDTO userDTO;

    private List<OrderItemDTO> orderFlowersList;

    private String status;

    private Integer discount;

    private BigDecimal finalPrice;

    OrderDTO(){}


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

    public List<OrderItemDTO> getOrderFlowersList() {
        return orderFlowersList;
    }

    public void setOrderFlowersList(List<OrderItemDTO> orderFlowersList) {
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
