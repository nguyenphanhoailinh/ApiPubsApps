package com.example.SpringDemo.models;

import jakarta.persistence.ManyToMany;

public class OrderDetail {
	private Long idoderdetail;
	@ManyToMany
	private Order idoder;
	
}
