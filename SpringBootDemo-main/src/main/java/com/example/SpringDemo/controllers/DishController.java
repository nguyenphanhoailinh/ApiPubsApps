package com.example.SpringDemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.SpringDemo.services.DishService;

@RestController
@RequestMapping("/api/v1/dishs")
public class DishController {
	
	@Autowired 
	private DishService dishService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Dish>> getAllDish()
	{
		List<Dish> dish = dishService.getAllDish();
		return ResponseEntity.ok(dish);
	}
	
	  @PostMapping("/create")
	  public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
	    Dish create = dishService.createDish(dish);
	    return ResponseEntity.ok(create);
	  }
	  
	  @PutMapping("/update/{id}")
	  public ResponseEntity<Dish> updateDish(@RequestBody Dish dish, @PathVariable Long id) {
	    dish.setIddish(id);
	    Dish updated = dishService.updateDish(dish);
	    return ResponseEntity.ok(updated);  
	  }
	  @DeleteMapping("/delete/{id}") 
	  public ResponseEntity<String> deleteDish(@PathVariable Long id) {
	    dishService.deleteDish(id);
	    return ResponseEntity.ok("Table deleted");
	  }
	  
}
