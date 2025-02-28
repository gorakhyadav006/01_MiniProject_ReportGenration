package com.insureance.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insureance.entity.CitizenPlan;
import com.insureance.repo.CitizenPlanReository;
import com.insureance.request.SearchRequest;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private CitizenPlanReository planRepo;
	
	@Override
	public List<String> getPlanNames() {
		List<String> planNames = planRepo.getPlanName();
		return planNames;
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request){
		
		CitizenPlan entity = new CitizenPlan();
		
		if(null != request.getPlanName()&& !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		
		if(null != request.getPlanStatus()&& !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null != request.getGender()&& !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null != request.getPlanStartDate()&& !"".equals(request.getPlanStartDate())) {
			String startDate = request.getPlanStartDate();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			//convert String to Localdate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			
			entity.setPlanStartDate(localDate);
		}
		
		if(null != request.getPlanEndDate()&& !"".equals(request.getPlanEndDate())) {
			String endDate = request.getPlanEndDate();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
			//convert String to Localdate
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			
			entity.setPlanStartDate(localDate);
		}
		
		
		
		//BeanUtils.copyProperties(request, entity);
		
		//sExample<CitizenPlan> example = Example.of(entity);
		
		
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return true;
	}

}
