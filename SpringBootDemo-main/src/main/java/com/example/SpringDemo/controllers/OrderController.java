package com.example.SpringDemo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.SpringDemo.models.*;
import com.example.SpringDemo.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringDemo.services.DishService;
import com.example.SpringDemo.services.OrderService;
import com.example.SpringDemo.services.UserService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	@Autowired
	private TableService tableService;
	@Autowired
	private OrderService orderService;

	@Autowired
	private DishService dishService;

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	@PostMapping("/create")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {

		try {
			// Assign user and table entities based on IDs
			User user = userService.getByUsername(order.getUser().getUsername());
			TableEntity table = tableService.getTableById(order.getTable().getIdtable());
			order.setUser(user);
			order.setTable(table);

			// Set current date/time for ngaygiodat
			order.setNgaygiodat(new Date());

			// Handle potential exceptions from service calls
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		// Create the order using the service
		Order createdOrder = orderService.createOrder(order);

		// Return the created order with a success status
		return ResponseEntity.ok(createdOrder);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id) {
		order.setIdorder(id);
		Order updated = orderService.updateOrder(order);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.ok("Order deleted");
	}
}
