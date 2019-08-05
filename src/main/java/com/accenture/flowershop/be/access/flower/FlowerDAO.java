package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerDAO {
    Flower getFlowerById(Long id);
    List<Flower> getAllFlowers() throws InternalException;
    void updateQtyStock(Flower flower) throws InternalException;
    List<Flower> searchFlower(String request) throws InternalException;
    void addFlowerQTY(Integer flowerQTY);
}

