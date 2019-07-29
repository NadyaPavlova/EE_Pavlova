package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.fe.ws.FlowerStockWebService")
public class FlowerStockWebServiceImpl implements FlowerStockWebService {

    @Autowired
    private FlowerBusinessService fbs;

    public FlowerStockWebServiceImpl(){}

    @Override
    @Transactional
    public boolean increaseFlowerStockSize(Integer flowerQTY) {
        try {
            fbs.addFlowerQTY(flowerQTY);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}