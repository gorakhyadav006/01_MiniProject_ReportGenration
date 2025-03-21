package com.insureance.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.insureance.entity.CitizenPlan;
import com.insureance.request.SearchRequest;
import com.insureance.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;

	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "search";
	}

	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment; filename=plans.xls");
		service.exportExcel(response);
	}
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition","attachment; filename=plans.pdf");
		service.exportPdf(response);
	}
	
	
	
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model) {
		init(model);
		List<CitizenPlan> plans = service.search(search);
//		for (CitizenPlan citizenPlan : plans) {
//			System.out.println(citizenPlan.getCitizenId());
//		}
		model.addAttribute("plans", plans);
		System.out.println(search);
		return "search";
	}

	private void init(Model model) {
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatuses());
	}
	
	

}
