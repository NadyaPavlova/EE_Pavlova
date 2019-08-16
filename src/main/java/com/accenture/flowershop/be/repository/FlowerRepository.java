package com.accenture.flowershop.be.repository;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface FlowerRepository extends JpaRepository<Flower, Long> {
    Flower getFlowerById(Long id);

    List<Flower> findAll();

    @Query(value = "select f from Flower f where f.nameFlower like %:name% and f.price between :minPrice and :maxPrice")
    List<Flower> searchFlower(@Param("name") String name, @Param("minPrice") BigDecimal min, @Param("maxPrice") BigDecimal max);
}
