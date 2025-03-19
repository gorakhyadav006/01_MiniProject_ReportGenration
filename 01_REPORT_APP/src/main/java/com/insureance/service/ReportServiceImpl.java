package com.insureance.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.insureance.entity.CitizenPlan;
import com.insureance.repo.CitizenPlanReository;
import com.insureance.request.SearchRequest;
import com.insureance.util.EmailUtils;
import com.insureance.util.ExcelGenrator;
import com.insureance.util.PdfGenrator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanReository planRepo;
	
	@Autowired
	ExcelGenrator exGen;
	
	@Autowired
	PdfGenrator pdfGen;
	
	@Autowired
	EmailUtils emailUtil;
	

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
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}

		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
			String startDate = request.getPlanStartDate();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to Localdate
			LocalDate localDate = LocalDate.parse(startDate, formatter);

			entity.setPlanStartDate(localDate);
		}

		if (null != request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String endDate = request.getPlanEndDate();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to Localdate
			LocalDate localDate = LocalDate.parse(endDate, formatter);

			entity.setPlanEndDate(localDate);
		}

		// BeanUtils.copyProperties(request, entity);

		// sExample<CitizenPlan> example = Example.of(entity);

		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plan = planRepo.findAll();
		File file = new File("Plans.xls");
		exGen.genrateExcel(plan, response, file);
		
		
		String sub ="Test Mail From Boot";
		String body="<h1>This is test mail for you</h1>";
		String emailAdd="ygorakh@gmail.com";
		
		
		emailUtil.sendEmail(sub,body, emailAdd, file);
		
		//deleting Plans.xls file after sending email
		file.delete();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plan = planRepo.findAll();

		  return pdfGen.genratePdf(plan, response);
	}

}
