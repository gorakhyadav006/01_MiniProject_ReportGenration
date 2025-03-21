package com.insureance.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.insureance.entity.CitizenPlan;
import com.insureance.request.SearchRequest;

public interface ReportService {


	//1. method to get plan-name drop down data
	public List<String> getPlanNames();
	
	//2. method to get plan-status drop down data
	public List<String> getPlanStatuses();
	
	//3.Method to handle search functionality
	
	public List<CitizenPlan> search(SearchRequest request);
	
	//4.Method to export data to pdf file
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
	
	//5.Method to export data to excel file
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*bellow are done by Query by example DataJpa method no need in service
	
	public List<CitizenPlan> getByPlanName(String planName);
	
	public List<CitizenPlan> getByPlanStatus(String planStatus);

	public List<CitizenPlan> getByGender(String gender);

	public List<CitizenPlan> getByStartDate(Date date);

	public List<CitizenPlan> getByEndDate(Date date);
	public List<CitizenPlan> getAllCitizenData();*/
	
	
	
	
	

}
