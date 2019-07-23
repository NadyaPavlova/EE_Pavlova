package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
    private FlowerDTO flowerDTO;
    private BigDecimal priceFlower;
    private int qtyFlower;

    public OrderItemDTO() {
    }

    public FlowerDTO getFlowerDTO() {
        return flowerDTO;
    }

    public void setFlowerDTO(FlowerDTO flowerDTO) {
        this.flowerDTO = flowerDTO;
    }

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
}
