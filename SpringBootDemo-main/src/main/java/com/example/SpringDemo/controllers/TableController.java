package com.example.SpringDemo.controllers;

import java.util.List;

import com.example.SpringDemo.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/{tableId}/dishes")
	public ResponseEntity<TableEntity> addDishesToTable(
			@PathVariable Long tableId,
			@RequestBody List<Long> dishIds) {
		TableEntity table = tableService.addDishesToTable(tableId, dishIds);
		return new ResponseEntity<>(table, HttpStatus.OK);
	}
	@PostMapping("/{tableName}/update-status")
	public ResponseEntity<TableEntity> updateTableStatus(@PathVariable String tableName,
														 @RequestParam Status newStatus) {
		TableEntity updatedTable = tableService.updateTableStatus(tableName, newStatus);
		return ResponseEntity.ok(updatedTable);
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