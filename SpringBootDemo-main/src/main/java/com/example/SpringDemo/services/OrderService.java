package com.example.SpringDemo.services;

import java.util.List;

import com.example.SpringDemo.models.Status;
import com.example.SpringDemo.models.TableEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.SpringDemo.models.Order;

import com.example.SpringDemo.repositories.OrderRepository;
import com.example.SpringDemo.repositories.TableRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private TableRepository tableRepository;
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}
//	public List<Order> getOrdersByTableId(Long idtable) {
//        return orderRepo.findByTableId(idtable);
//    }
	public Order getOrdersByTableId(Long idtable) {
        return orderRepo.findByTable_Idtable(idtable);
    }
	public String getTableNameByTableId(Long idtable) {
        TableEntity table = tableRepository.findByIdtable(idtable);
        return table != null ? table.getNametable() : null;
    }
	public Order getOrderById(Long id) {
		return orderRepo.findById(id).orElseThrow(() -> new ResourceAccessException("Order not found"));
	}

	public Order createOrder(Order order) {
		order.setStatus(Status.dangSuDung); // Set default status to PENDING
		return orderRepo.save(order);
	}
	public Order updateOrder(Order order) {
		return orderRepo.save(order);
	}

	public void deleteOrder(Long id) {
		orderRepo.deleteById(id);
	}
}
