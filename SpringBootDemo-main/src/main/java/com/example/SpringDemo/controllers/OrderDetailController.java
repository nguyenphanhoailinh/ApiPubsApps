package com.example.SpringDemo.controllers;

import com.example.SpringDemo.models.*;
import com.example.SpringDemo.services.DishService;
import com.example.SpringDemo.services.OrderDetailService;
import com.example.SpringDemo.services.OrderService;
import com.example.SpringDemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orderdetails")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private DishService dishService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetails);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody Map<String, String> body) {
        OrderDetail orderDetail = new OrderDetail();

        String dishIds = body.get("dishes");
        if (dishIds != null) {
            List<Dish> dishes = new ArrayList<>();
            for (String dishId : dishIds.split(",")) {
                Dish dish = dishService.getDishById(Long.parseLong(dishId.trim()));
                if (dish != null) {
                    dishes.add(dish);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            orderDetail.setDishes(dishes);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Long quantity = Long.parseLong(body.get("quantity"));
        orderDetail.setQuantity(quantity);

        BigDecimal totalPrice = new BigDecimal(body.get("totalPrice"));
        orderDetail.setTotalPrice(totalPrice);

        Order order = orderService.getOrderById(Long.parseLong(body.get("orderId")));
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setOrder(order);

        OrderDetail createdOrderDetail = orderDetailService.createOrderDetail(orderDetail);

        return ResponseEntity.ok(createdOrderDetail);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@RequestBody OrderDetail orderDetail, @PathVariable Long id) {
        orderDetail.setIdOrderDetail(id);
        OrderDetail updatedOrderDetail = orderDetailService.updateOrderDetail(orderDetail);
        return ResponseEntity.ok(updatedOrderDetail);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.ok("OrderDetail deleted");
    }
}
