package com.example.SpringDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringDemo.models.ReportOrder;

@Repository
public interface ReportOrderRepository extends JpaRepository<ReportOrder, Long> {

}
