package com.example.SpringDemo.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idorder;

	@ManyToMany
	private List<Dish> dishes;

	@Column(name = "ngaygiodat")
	private Date ngaygiodat;
	@ManyToOne
	@JoinColumn(name = "idtable", nullable = false)
	private TableEntity table;

	@Column(name = "status", length = 50)
	private Status status;
	private double totalAmount;
}