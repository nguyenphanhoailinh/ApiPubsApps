package com.example.SpringDemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.SpringDemo.models.Bill;
import com.example.SpringDemo.repositories.BillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillService {
	@Autowired
	private BillRepository billRepository;

	public List<Bill> getAllBill(){
		return billRepository.findAll();
	}

	public Bill getByIdReportOrder(Long idbill) {

		return billRepository.findById(idbill).orElseThrow(() -> new ResourceAccessException("Bill not found"));
	}

	public Bill saveBill(Bill bill) {

		return billRepository.save(bill);
	}

}
