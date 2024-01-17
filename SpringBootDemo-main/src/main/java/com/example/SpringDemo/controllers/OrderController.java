package com.example.SpringDemo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.SpringDemo.models.*;
import com.example.SpringDemo.repositories.DishRepository;
import com.example.SpringDemo.repositories.OrderRepository;
import com.example.SpringDemo.repositories.TableRepository;
import com.example.SpringDemo.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringDemo.services.DishService;
import com.example.SpringDemo.services.OrderService;
import com.example.SpringDemo.services.ReportOrderService;
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
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ReportOrderService reportOrderService;
	@Autowired
	TableRepository tableRepository;

	@GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok().body(orders);
    }
	@GetMapping("/{idtable}")
    public ResponseEntity<List<Order>> getOrdersByTableId(@PathVariable Long idtable) {
        List<Order> orders = orderService.getOrdersByTableId(idtable);
        return ResponseEntity.ok().body(orders);
    }
	@PostMapping("/new")
	public ResponseEntity<Order> createOrder(@RequestBody OrderDish orderdish) {
		
		System.out.print(orderdish);
		
		try {
			List<Dish> dishes = dishService.getlistidDish(orderdish.getIddish());

			Order _order = new Order();
			_order.setDishes(dishes);
			_order.setNgaygiodat(orderdish.getNgaygiodat());

			TableEntity table = tableRepository.getById(orderdish.getIdtable());
			// Sau đó gán cho _order
			_order.setTable(table);

			_order.setTotalAmount(orderdish.getTotalAmount());
			System.out.print(_order);
			orderService.createOrder(_order);
			return new ResponseEntity<>(_order, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id) {
		order.setIdorder(id);
		Order updated = orderService.updateOrder(order);
		return ResponseEntity.ok(updated);
	}

//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
//		Order order =orderService.getOrderById(id);
//		if(order == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
//		}
//		ReportOrder reportOrder = new ReportOrder();
//		reportOrder.setIdorder(order.getIdorder());
//		reportOrder.setDeletedAt(new Date());
//		reportOrderService.saveReportOrder(reportOrder);
//		orderService.deleteOrder(id);
//		return ResponseEntity.ok("Order deleted ");
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
	    Order order = orderService.getOrderById(id);
	    if(order == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
	    }
	    ReportOrder reportOrder = new ReportOrder();
	    reportOrder.setIdorder(order.getIdorder());
	    reportOrder.setDishes(order.getDishes().stream().map(dish -> {
	        DishInfo dishInfo = new DishInfo();
	        dishInfo.setDishname(dish.getNamedish());
	        dishInfo.setPrice(dish.getPrice());
	        return dishInfo;
	    }).collect(Collectors.toList()));
	    reportOrder.setNgaygiodat(order.getNgaygiodat());
	    reportOrder.setTableId(order.getTable().getIdtable());
	    reportOrder.setStatus(order.getStatus().toString());
	    reportOrder.setTotalAmount(order.getTotalAmount());
	    reportOrder.setDeletedAt(new Date());
	    reportOrderService.saveReportOrder(reportOrder);
	    orderService.deleteOrder(id);
	    return ResponseEntity.ok("Order deleted and saved to ReportOrder");
	}
	@GetMapping("/report/{id}")
	public ResponseEntity<?> getReportOrder(@PathVariable Long id){
		
		ReportOrder report = reportOrderService.getByIdReportOrder(id);
		return ResponseEntity.ok(report);
	}
}
