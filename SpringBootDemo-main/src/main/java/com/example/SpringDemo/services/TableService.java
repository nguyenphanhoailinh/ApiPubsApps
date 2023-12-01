package com.example.SpringDemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.SpringDemo.dto.TableRequest;
import com.example.SpringDemo.models.TableEntity;
import com.example.SpringDemo.repositories.TableRepository;
import com.example.SpringDemo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableService {

	@Autowired
	private TableRepository tableRepo;

	public List<TableEntity> getAllTables() {
		return tableRepo.findAll();
	}

	public TableEntity getTableById(Long id) {
		return tableRepo.findById(id).orElseThrow(() -> new ResourceAccessException("Table not found"));
	}

	public TableEntity createTable(TableEntity table) {
		return tableRepo.save(table);
	}

	public TableEntity updateTable(TableEntity table) {
		return tableRepo.save(table);
	}

	public void deleteTable(Long id) {
		tableRepo.deleteById(id);
	}

}