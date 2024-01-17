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
import com.example.SpringDemo.services.BillService;
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
	private BillService billService;
	@Autowired
	TableRepository tableRepository;

	@GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
	@GetMapping("/{idtable}")
    public ResponseEntity<Order> getOrdersByTableId(@PathVariable Long idtable) {
        Order orders = orderService.getOrdersByTableId(idtable);
        return ResponseEntity.ok().body(orders);
    }
	@PostMapping("/new")
	public ResponseEntity<Order> createOrder(@RequestBody OrderDish orderdish) {
		
		
		
		try {
			List<Dish> dishes = dishService.getlistidDish(orderdish.getIddish());
			Order _order = new Order();
			TableEntity table = tableRepository.getById(orderdish.getIdtable());
			
			if(table.getStatus().toString().equals("dangSuDung")) {
				_order =orderService.getOrdersByTableId(table.getIdtable());
				_order.getDishes().addAll(dishes);
				_order.setDishes(_order.getDishes());
				orderService.updateOrder(_order);
			}else {
				_order.setDishes(dishes);
				_order.setNgaygiodat(orderdish.getNgaygiodat());

				
				// Sau đó gán cho _order
				_order.setTable(table);

				_order.setTotalAmount(orderdish.getTotalAmount());
				orderService.createOrder(_order);
				table.setStatus(Status.dangSuDung);
				tableService.updateTable(table);
			}
			
			
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
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
	    Order order = orderService.getOrderById(id);
	    TableEntity table = order.getTable();
	    if(order == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
	    }
	    Bill bill = new Bill();
	    bill.setIdorder(order.getIdorder());
	    bill.setDishes(order.getDishes().stream().map(dish -> {
	        DishInfo dishInfo = new DishInfo();
	        dishInfo.setDishname(dish.getNamedish());
	        dishInfo.setPrice(dish.getPrice());
	        return dishInfo;
	    }).collect(Collectors.toList()));
	    bill.setNgaygiodat(order.getNgaygiodat());
	    bill.setIdtable(order.getTable().getIdtable());
	    bill.setTablename(order.getTable().getNametable());
	    bill.setTotalAmount(order.getTotalAmount());
	    bill.setDeletedAt(new Date());
	    billService.saveBill(bill);
	    orderService.deleteOrder(id);
	    table.setStatus(Status.conTrong);
		tableService.updateTable(table);
	    return ResponseEntity.ok("Order deleted and saved to bill");
	}
	@GetMapping("/report/{id}")
	public ResponseEntity<?> getBillById(@PathVariable Long id){
		
		Bill bill = billService.getByIdReportOrder(id);
		return ResponseEntity.ok(bill);
	}
	@GetMapping("/bill/all")
	public ResponseEntity<List<Bill>> getAllBill() {
        List<Bill> bills = billService.getAllBill();
        return ResponseEntity.ok(bills);
    }

}
