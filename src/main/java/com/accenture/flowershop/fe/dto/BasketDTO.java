package com.accenture.flowershop.fe.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketDTO {

    private List<OrderItemDTO> basketList = new ArrayList<>();

    private BigDecimal priceSum;

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }

    public BasketDTO() {
    }

    public List<OrderItemDTO> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<OrderItemDTO> basketList) {
        this.basketList = basketList;
    }

    public BasketDTO(List<OrderItemDTO> basketList) {
        this.basketList = basketList;
    }


}
