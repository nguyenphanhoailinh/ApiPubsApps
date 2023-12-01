package com.example.SpringDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringDemo.models.Dish;
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

}
