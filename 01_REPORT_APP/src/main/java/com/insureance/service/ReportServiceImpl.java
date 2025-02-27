package com.insureance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPlanStatuses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request){
		// TODO Auto-generated method stub
		return null;
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
