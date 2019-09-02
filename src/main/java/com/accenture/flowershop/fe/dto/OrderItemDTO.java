package com.accenture.flowershop.fe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dozer.Mapping;

import java.math.BigDecimal;

public class OrderItemDTO {
    private FlowerDTO flowerDTO;
    private BigDecimal priceFlower;
    private int qtyFlower;
    @JsonIgnore
    private OrderDTO orderDTO;
    public OrderItemDTO() {
    }
    @Mapping("flower")
    public FlowerDTO getFlowerDTO() {
        return flowerDTO;
    }

    public void setFlowerDTO(FlowerDTO flowerDTO) {
        this.flowerDTO = flowerDTO;
    }
    @Mapping("price")
    public BigDecimal getPriceFlower() {
        return priceFlower;
    }

    public void setPriceFlower(BigDecimal priceFlower) {
        this.priceFlower = priceFlower;
    }

    public int getQtyFlower() {
        return qtyFlower;
    }

    public void setQtyFlower(int qtyFlower) {
        this.qtyFlower = qtyFlower;
    }
    @Mapping("order")
    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
}
