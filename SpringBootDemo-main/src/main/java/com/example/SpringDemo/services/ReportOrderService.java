package com.example.SpringDemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.SpringDemo.models.ReportOrder;
import com.example.SpringDemo.repositories.ReportOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportOrderService {
	@Autowired
	private ReportOrderRepository reportOrderRepository;

	public List<ReportOrder> getAllReportOrder() {

		return reportOrderRepository.findAll();

	}
	
	public ReportOrder getByIdReportOrder(Long idreport) {
		
		return reportOrderRepository.findById(idreport).orElseThrow(() -> new ResourceAccessException("ReportOrder not found"));
	}
	
	public ReportOrder saveReportOrder(ReportOrder reportOrder) {
		
		return reportOrderRepository.save(reportOrder);
	}

}
