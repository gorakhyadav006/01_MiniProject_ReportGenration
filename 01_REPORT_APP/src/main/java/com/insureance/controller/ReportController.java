package com.insureance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.insureance.service.ReportService;


@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
//	@GetMapping("/index.jsp")
//	public String indexPage() {
//		return "index";
//	}
//	
	@GetMapping("/")
	public String searchPage() {
		System.out.println("fdsafdsa");
		return "search";
		
	}
	
}
