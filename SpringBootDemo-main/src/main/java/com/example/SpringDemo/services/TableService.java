package com.example.SpringDemo.services;

import java.util.List;

import com.example.SpringDemo.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.SpringDemo.dto.TableRequest;
import com.example.SpringDemo.models.Dish;
import com.example.SpringDemo.models.TableEntity;
import com.example.SpringDemo.repositories.DishRepository;
import com.example.SpringDemo.repositories.TableRepository;
import com.example.SpringDemo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableService {

	@Autowired
	private TableRepository tableRepo;
	@Autowired
	private DishRepository dishRepo;
	public List<TableEntity> getAllTables() {
		return tableRepo.findAll();
	}

	public TableEntity getTableById(Long id) {
		return tableRepo.findById(id).orElseThrow(() -> new ResourceAccessException("Table not found"));
	}

	public TableEntity createTable(TableEntity table) {
		return tableRepo.save(table);
	}
	public TableEntity addDishToTable(Long tableId, Long dishId) {
	    // Tìm bàn với id đã cho
	    TableEntity table = tableRepo.findById(tableId)
	        .orElseThrow();

	    // Tìm món ăn với id đã cho
	    Dish dish = dishRepo.findById(dishId)
	        .orElseThrow();

	    // Thêm món ăn vào bàn

	    // Lưu và trả về bàn đã được cập nhật
	    return tableRepo.save(table);
	}

	public TableEntity updateTable(TableEntity table) {
		return tableRepo.save(table);
	}

	public void deleteTable(Long id) {
		tableRepo.deleteById(id);
	}

	public TableEntity addDishesToTable(Long tableId, List<Long> dishIds) {
		TableEntity table = tableRepo.findById(tableId)
				.orElseThrow();

		List<Dish> dishes = dishRepo.findAllById(dishIds);

		return tableRepo.save(table);
	}
	public TableEntity updateTableStatus(String tableName, Status newStatus) {
		TableEntity table = tableRepo.findByNametable(tableName)
				.orElseThrow(() -> new ResourceAccessException("Table not found"));

		table.setStatus(newStatus);
		return tableRepo.save(table);
	}

}