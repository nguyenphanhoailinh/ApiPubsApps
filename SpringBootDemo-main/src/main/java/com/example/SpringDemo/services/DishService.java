package com.example.SpringDemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.SpringDemo.models.Dish;

import com.example.SpringDemo.repositories.DishRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DishService {
	@Autowired
	private DishRepository dishRepo;
	
	public List<Dish> getAllDish() {
		return dishRepo.findAll();
	}

	public Dish getDishById(Long id) {
		return dishRepo.findById(id).orElseThrow(() -> new ResourceAccessException("Table not found"));
	}

	public Dish createDish(Dish table) {
		return dishRepo.save(table);
	}

	public Dish updateDish(Dish table) {
		return dishRepo.save(table);
	}

	public void deleteDish(Long id) {
		dishRepo.deleteById(id);
	}
}
