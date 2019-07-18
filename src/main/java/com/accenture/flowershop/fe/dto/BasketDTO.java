package com.accenture.flowershop.fe.dto;

import java.util.ArrayList;
import java.util.List;

public class BasketDTO {

    private List<Order_itemDTO> basketList = new ArrayList<>();

    public BasketDTO() {
    }

    public List<Order_itemDTO> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Order_itemDTO> basketList) {
        this.basketList = basketList;
    }

    public void deleteFlower(Long flower_id) {
        for (Order_itemDTO fl : basketList) {
            if (fl.getId_flower()==flower_id){
                basketList.remove(fl);
            }
        }
    }
    public BasketDTO(List<Order_itemDTO> basketList) {
        this.basketList = basketList;
    }

    public void addBasket(FlowerDTO flower){
        for (Order_itemDTO fl : basketList) {
            if (fl.getId_flower() == flower.getId_flower()) {
                fl.setQty_fl(fl.getQty_fl() + 1);
                return;
            }
        }
        Order_itemDTO order_flowers = new Order_itemDTO(flower.getId_flower(),flower.getName_flower(),flower.getPrice());
        basketList.add(order_flowers);
    }

    public void deleteBasket(FlowerDTO flower){
        for (Order_itemDTO fl : basketList) {
            if (fl.getId_flower() == flower.getId_flower()) {
                fl.setQty_fl(fl.getQty_fl() - 1);
            }
            if (fl.getQty_fl()==0){
                basketList.remove(fl);
                return;
            }
        }
    }
}
