package com.example.SpringDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringDemo.models.TableEntity;
@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long>{

}