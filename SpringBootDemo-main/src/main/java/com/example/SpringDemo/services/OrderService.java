package com.example.SpringDemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.SpringDemo.models.Order;

import com.example.SpringDemo.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}
	public Order getOrderById(Long id) {
		return orderRepo.findById(id).orElseThrow(() -> new ResourceAccessException("Order not found"));
	}

	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}

	public Order updateOrder(Order order) {
		return orderRepo.save(order);
	}

	public void deleteOrder(Long id) {
		orderRepo.deleteById(id);
	}
}
