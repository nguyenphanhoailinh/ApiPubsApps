package com.example.SpringDemo.controllers;

import java.util.List;

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

import com.example.SpringDemo.dto.JwtAuthenticationResponse;
import com.example.SpringDemo.dto.SignUpRequest;
import com.example.SpringDemo.dto.TableRequest;
import com.example.SpringDemo.models.Dish;
import com.example.SpringDemo.models.TableEntity;
import com.example.SpringDemo.repositories.TableRepository;
import com.example.SpringDemo.services.DishService;
import com.example.SpringDemo.services.TableService;

@RestController
@RequestMapping("/api/v1/tables")
public class TableController {
	
	@Autowired
	  private TableService tableService;
	@Autowired 
		private DishService dishService;
	@GetMapping("")
	public ResponseEntity<List<TableEntity>> getAllTables() {

	  List<TableEntity> tables = tableService.getAllTables();

	  return ResponseEntity.ok(tables);

	}

	@GetMapping("/{id}")
	public ResponseEntity<TableEntity> getTableById(@PathVariable Long id) {

	  TableEntity table = tableService.getTableById(id);

	  return ResponseEntity.ok(table);

	}
	
	  @PostMapping("/create")
	  public ResponseEntity<TableEntity> createTable(@RequestBody TableEntity table) {
	    TableEntity created = tableService.createTable(table);
	    return ResponseEntity.ok(created);
	  }
	  
	  @PostMapping("/{tableid}/dishes/{dishid}")
	  public void adDishToTable(@PathVariable Long tableid,@PathVariable Long dishid) {
		  Dish dish = dishService.getDishById(dishid);
		  if(dish != null) {
			  
		  }
	  }
	  
	  @PutMapping("/update/{id}")
	  public ResponseEntity<TableEntity> updateTable(@RequestBody TableEntity table, @PathVariable Long id) {
	    table.setIdtable(id);
	    TableEntity updated = tableService.updateTable(table);
	    return ResponseEntity.ok(updated);  
	  }

	  @DeleteMapping("/delete/{id}") 
	  public ResponseEntity<String> deleteTable(@PathVariable Long id) {
	    tableService.deleteTable(id);
	    return ResponseEntity.ok("Table deleted");
	  }
}