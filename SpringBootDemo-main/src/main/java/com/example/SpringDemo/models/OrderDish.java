package com.example.SpringDemo.models;

import java.sql.Date;
import java.util.List;

import lombok.Data;
@Data
public class OrderDish {

	private List<Long> iddish;

	private Date ngaygiodat;

	private Long idtable;
	private double totalAmount;

}
