package com.example.SpringDemo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
	
	String uploadDir = "src/main/resources/static/images/";
	
	public List<Dish> getAllDish() {
		return dishRepo.findAll();
	}
	public InputStreamResource getimages(String imagefilename) throws IOException
	{
			Path imagePath =Paths.get(uploadDir + imagefilename);
			return new InputStreamResource(Files.newInputStream(imagePath));
		
	}
	public boolean existsById(Long id) {
        return dishRepo.existsById(id);
    }
	public Dish getDishById(Long id) {
		return dishRepo.findById(id).orElseThrow(() -> new ResourceAccessException("Dish not found"));
	}
	public List<Dish> getlistidDish(List<Long> id){
		return dishRepo.findByIdsIndd(id);
	}
	public Dish createDish(Dish dish) {
		return dishRepo.save(dish);
	}

	public Dish updateDish(Dish dish) {
		return dishRepo.save(dish);
	}

	public void deleteDish(Long id) {
		dishRepo.deleteById(id);
	}

}
