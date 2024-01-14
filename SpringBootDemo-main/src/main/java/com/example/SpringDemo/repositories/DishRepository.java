package com.example.SpringDemo.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.SpringDemo.models.Dish;
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

	@Query("SELECT d FROM dishs d WHERE d.iddish IN :ids")
	   List<Dish> findByIdsIndd(@Param("ids") List<Long> ids);
	
}
