package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerBusinessService {
     List<Flower> getAllFlowers();
     Flower getFlowerById(Long id);
     void countingFlowers(Flower flower, int qty);
}
