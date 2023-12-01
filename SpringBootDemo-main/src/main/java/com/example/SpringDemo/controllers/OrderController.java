package com.example.SpringDemo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringDemo.models.Dish;
import com.example.SpringDemo.models.Order;
import com.example.SpringDemo.models.Status;
import com.example.SpringDemo.models.User;
import com.example.SpringDemo.services.DishService;
import com.example.SpringDemo.services.OrderService;
import com.example.SpringDemo.services.UserService;




@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	@Autowired 
	private OrderService orderService;
	@Autowired 
	private DishService dishService;
	@Autowired 
	private UserService userService;
	@GetMapping("/all")
	public ResponseEntity<List<Order>> getAllDish()
	{
		List<Order> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}
	
//	  @PostMapping("/create")
//	  public ResponseEntity<Order> createDish(@RequestBody Order order) {
//	    Order create = orderService.createOrder(order);
//	    return ResponseEntity.ok(create);
//	  }
	  
//	@PostMapping("/create")
//	public ResponseEntity<Order> createDonHang(@RequestBody Map<String, String> body) {
//		Order donHang = new Order();
//	    
//	    Dish dish = dishService.getDishById(Long.parseLong(body.get("dish"))); // lấy món ăn
//	    User user = userService.getById(Long.parseLong(body.get("user"))); // lấy user
//
//	    donHang.setDish(dish);
//	    donHang.setUser(user);
//	    try {
//	        donHang.setNgaygiodat(new SimpleDateFormat("dd/MM/yyyy").parse(body.get("ngaygiodat")));
//	    } catch (ParseException e) {
//	        e.printStackTrace();
//	    }
//	    Status status = Status.valueOf(body.get("status").toUpperCase());
//	    donHang.setStatus(status);
//
//	    Order createdDonHang = orderService.createOrder(donHang); // assuming you have a service to save DonHang
//
//	    return ResponseEntity.ok(createdDonHang);
//	}
	
	//Oke
	@PostMapping("/create")
	public ResponseEntity<Order> createDonHang(@RequestBody Map<String, String> body) {
	    Order donHang = new Order();
	    
	    String dishIds = body.get("dishes");
	    if (dishIds != null) {
	        List<Dish> dishes = new ArrayList<>();
	        for (String dishId : dishIds.split(",")) {
	            Dish dish = dishService.getDishById(Long.parseLong(dishId.trim()));
	            if (dish != null) {
	                dishes.add(dish);
	            } else {
	                return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	            }
	        }
	        donHang.setDishes(dishes);
	    } else {
	        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
	    }

	    User user = userService.getById(Long.parseLong(body.get("user"))); // assuming you have a service to fetch User
	    if (user == null) {
	        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	    }
	    donHang.setUser(user);

	    try {
	        donHang.setNgaygiodat(new SimpleDateFormat("dd/MM/yyyy").parse(body.get("ngaygiodat")));
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
	    }

	    Status status = Status.valueOf(body.get("status").toUpperCase());
	    donHang.setStatus(status);

	    Order createdDonHang = orderService.createOrder(donHang); // assuming you have a service to save DonHang

	    return ResponseEntity.ok(createdDonHang);
	}


	  @PutMapping("/update/{id}")
	  public ResponseEntity<Order> updateDish(@RequestBody Order order, @PathVariable Long id) {
		  order.setIdorder(id);
		  Order updated = orderService.updateOrder(order);
	    return ResponseEntity.ok(updated);  
	  }
	  @DeleteMapping("/delete/{id}") 
	  public ResponseEntity<String> deleteDish(@PathVariable Long id) {
	    orderService.deleteOrder(id);
	    return ResponseEntity.ok("Order deleted");
	  }
}
