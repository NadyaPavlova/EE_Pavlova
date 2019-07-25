package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.business.InternalException;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerBusinessService {
     List<Flower> getAllFlowers() throws InternalException;
     Flower getFlowerById(Long id);
     void countingFlowers(Flower flower, int qty) throws InternalException;
     List<Flower> searchFlower(String request) throws InternalException;
}
