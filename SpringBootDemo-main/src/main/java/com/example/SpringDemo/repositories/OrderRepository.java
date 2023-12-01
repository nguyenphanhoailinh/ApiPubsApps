package com.example.SpringDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringDemo.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
