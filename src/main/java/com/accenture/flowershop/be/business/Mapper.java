package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.OrderItemDTO;

public class Mapper {


    public static FlowerDTO mapper(Flower flower){
        FlowerDTO flowerDTO= new FlowerDTO();
        flowerDTO.setIdFlower(flower.getIdFlower());
        flowerDTO.setNameFlower(flower.getNameFlower());
        flowerDTO.setPrice(flower.getPrice());
        flowerDTO.setQtyStock(flower.getQtyStock());
        return flowerDTO;
    }
    public static OrderItemDTO mapper(FlowerDTO flowerDTO){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setIdFlower(flowerDTO.getIdFlower());
        orderItemDTO.setNameFlower(flowerDTO.getNameFlower());
        return orderItemDTO;
    }
}
