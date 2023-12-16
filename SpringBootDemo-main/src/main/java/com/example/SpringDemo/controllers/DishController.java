package com.example.SpringDemo.controllers;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.InputStream;
import java.io.IOException;
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
//	
//	  @PostMapping("/create")
//	  public ResponseEntity<Dish> createDish(@RequestBody Dish dish,@RequestParam("image") MultipartFile imageFile) {
//	    try {
//	    	String uploadDir = "src/main/resources/static/images/";
//	    	
//	    	String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
//	    	Path uploadPath = Paths.get(uploadDir);
//			if (!Files.exists(uploadPath)) {
//				Files.createDirectories(uploadPath);
//			}
//			try (InputStream inputStream = imageFile.getInputStream()) {
//				Path filePath = uploadPath.resolve(filename);
//				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//			} catch (IOException ioe) {
//				throw new IOException("Could not save image file: " + filename, ioe);
//			}
//
//			// Set the product's image filename and save the product
//			dish.setImagefilename(filename);
//			dishService.createDish(dish);
//	    }catch (IOException e) {
//			e.printStackTrace();
//		  
//	    }
//	    return ResponseEntity.ok(dish);
//	  }
//	  
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createDish(@RequestParam("dishName") String  namedish, @RequestParam("price") float price , @RequestParam("image") MultipartFile imageFile) {
	    try {
	    	Dish dish =new Dish();
	        String uploadDir = "src/main/resources/static/images/";

	        String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
	        Path uploadPath = Paths.get(uploadDir);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	        try (InputStream inputStream = imageFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(filename);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ioe) {
	            throw new IOException("Could not save image file: " + filename, ioe);
	        }

	        // Set the product's image filename and save the product
	        String imageUrl = "http://localhost:8888/api/v1/dishs/" + filename;
	        dish.setImagefilename(imageUrl);
	        dish.setNamedish(namedish);
	        dish.setPrice(price);
	        dishService.createDish(dish);
	        return ResponseEntity.ok(dish);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return (ResponseEntity<?>) ResponseEntity.badRequest();
	    }
	    
	}
	
	@GetMapping("/{imageName}")
	public ResponseEntity<InputStreamResource> getImage(@PathVariable String imageName) throws IOException
	{
		InputStreamResource resource =dishService.getimages(imageName);
		return ResponseEntity.ok().body(resource);
	}

	
	  @PutMapping("/update/{id}")
	  @PreAuthorize("hasAuthority('admin')")
	  public ResponseEntity<Dish> updateDish(@RequestBody Dish dish, @PathVariable Long id) {
	    dish.setIddish(id);
	    Dish updated = dishService.updateDish(dish);
	    return ResponseEntity.ok(updated);  
	  }
	  @DeleteMapping("/delete/{id}") 
	  @PreAuthorize("hasAuthority('admin')")
	  public ResponseEntity<String> deleteDish(@PathVariable Long id) {
	    dishService.deleteDish(id);
	    return ResponseEntity.ok("Order deleted");
	  }
	  
}
