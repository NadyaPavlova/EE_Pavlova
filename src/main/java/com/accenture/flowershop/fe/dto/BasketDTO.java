package com.accenture.flowershop.fe.dto;

import java.util.ArrayList;
import java.util.List;

public class BasketDTO {

    private List<OrderItemDTO> basketList = new ArrayList<>();

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
