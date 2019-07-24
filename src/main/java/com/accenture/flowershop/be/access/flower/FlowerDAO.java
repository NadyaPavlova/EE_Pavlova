package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerDAO {
    Flower getFlowerById(Long id);
    Flower getFlowerByName(String name);
    List<Flower> getAllFlowers();
    void updateQtyStock(Flower flower);
    List<Flower> searchFlower(String request);
}

