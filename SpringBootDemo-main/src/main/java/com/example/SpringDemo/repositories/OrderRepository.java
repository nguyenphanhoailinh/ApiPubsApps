package com.example.SpringDemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringDemo.models.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
//	List<Order> findByTableId(Long idtable);
	List<Order> findByTable_Idtable(Long idtable);
}
