package com.accenture.flowershop.fe.ws.mvc;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping(value = "/flower")
public class FlowerController {
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    public FlowerController(){
        System.out.println("=== FlowerController создан ===");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Flower getFlowerById (@PathVariable("id") Long id) {
        return flowerBusinessService.getFlowerById(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flower> flowerFilter (@QueryParam("nameFlower") String nameFlower, @QueryParam("minPrice") String minPrice, @QueryParam("maxPrice") String maxPrice) {
        List<Flower> flowerList = flowerBusinessService.filterFlower(nameFlower, minPrice, maxPrice);
        return flowerList;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flower> flowerAll () {
        List<Flower> flowerList = flowerBusinessService.filterFlower("", "", "");
        return flowerList;
    }
}
