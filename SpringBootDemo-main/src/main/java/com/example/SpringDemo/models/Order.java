package com.example.SpringDemo.models;

import java.util.Date;
import java.util.List;

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
@Entity(name="orders")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idorder;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="iddish", nullable=false) private Dish dish;
	 */
	 @ManyToMany
	    private List<Dish> dishes;
//    private String namedish;
    @ManyToOne
    @JoinColumn(name="iduser", nullable=false)
    private User user;
//    private String fullname;
    @Column(name = "ngaygiodat")
    private Date ngaygiodat;

    @Column(name = "status", length = 50)
    private Status status;

}
