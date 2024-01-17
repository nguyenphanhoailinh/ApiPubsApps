package com.example.SpringDemo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bills")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idbill;

	@Column(name = "idorder")
	private Long idorder;
	@ElementCollection
	private List<DishInfo> dishes;
	@Column(name = "ngaygiodat")
	private Date ngaygiodat;

	@Column(name = "idtable")
	private Long idtable;
	@Column(name = "tablename")
	private String tablename;
	
	@Column(name = "total_amount")
	private double totalAmount;

	@Column(name = "deleted_at")
	private Date deletedAt;

}
