package com.example.SpringDemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringDemo.models.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Long>{

}
