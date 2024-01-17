package com.example.SpringDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringDemo.models.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
