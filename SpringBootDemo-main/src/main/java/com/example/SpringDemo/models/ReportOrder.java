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
@Entity(name = "report_orders")
public class ReportOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idreport;

	@Column(name = "idorder")
	private Long idorder;
	@ElementCollection
	private List<DishInfo> dishes;
	@Column(name = "ngaygiodat")
	private Date ngaygiodat;

	@Column(name = "idtable")
	private Long tableId; // assuming TableEntity has a getId() method

	@Column(name = "status", length = 50)
	private String status; // assuming Status has a toString() method

	@Column(name = "total_amount")
	private double totalAmount;

	@Column(name = "deleted_at")
	private Date deletedAt;

}
