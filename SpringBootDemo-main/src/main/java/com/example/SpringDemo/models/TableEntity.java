package com.example.SpringDemo.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tables")
public class TableEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Idtable;
	
	private String nametable;
	
	@ManyToMany
	public List<Dish> dish;
	@Enumerated(EnumType.STRING)
	private Status status;

}
