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
	private Long idtable;
	
	private String nametable;

	@Enumerated(EnumType.STRING)
	private Status status;

}
