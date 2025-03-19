 package com.fotp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fotp.binding.DashboardResponse;
import com.fotp.binding.EnquiryForm;
import com.fotp.binding.EnquirySearchCriteria;
import com.fotp.entity.StudentEnqEntity;
import com.fotp.service.EnquiryService;
import com.fotp.serviceimpl.EnquiryServiceImpl;

@Controller
public class Enquiryontroller {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private EnquiryServiceImpl enqService;
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	private void initForm(Model model) {
		         //1.get courses for drop down
				List<String> courseName = enqService.getCourses();
				//2.get enquiry status for drop down
				List<String> enqStatus = enqService.getEnqStatus();
				//3.create binding object
				EnquiryForm formObj = new EnquiryForm();
				//4. save the data in model object.
				model.addAttribute("courseName",courseName);
				model.addAttribute("enqStatus",enqStatus);
				model.addAttribute("formObj",formObj);
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		Integer userId = (Integer)session.getAttribute("userId");
		DashboardResponse dashboardData = enqService.getDashboardData(userId);
		
//		model.addAttribute("total", dashboard.getTotalEnquriesCnt());
//		model.addAttribute("enrolled", dashboard.getEnrolledCnt());
//		model.addAttribute("lost", dashboard.getLostCnt());

		model.addAttribute("dashboardData", dashboardData);
		return "dashboard";
	}
	
	@PostMapping("/addEnq")
	public String saveEnquiry(@ModelAttribute("formObj") EnquiryForm formObj,Model model) {
		Boolean save = enqService.upsertEnquiry(formObj);
		if(save) {
			model.addAttribute("succMsg", "Enquriy record inserted ......");
		}else {
			model.addAttribute("errMsg", "Some Error occured...");
		}
		System.out.print(formObj);	
		return "add-enquiry"; 
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		initForm(model);
		return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiryPage(Model model) {	       
		initForm(model);	
		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		List<StudentEnqEntity> enquries = enqService.getEnquries();
		model.addAttribute("enquries",enquries);
		model.addAttribute("criteria",criteria);
		return "view-enquries";
	}
	
	@GetMapping("/filter-enquiries")
	public String getfilteredEnqs(@RequestParam String cname,
			@RequestParam String status,@RequestParam String mode, Model model ){
		EnquirySearchCriteria criteria = new EnquirySearchCriteria();
		
		criteria.setCourse(cname);
		criteria.setEnqStatus(status);
		criteria.setClassMode(mode);
		System.out.println(criteria);	
		Integer userId = (Integer)session.getAttribute("userId");
		List<StudentEnqEntity> enquries = enqService.getfilteredEnquries(criteria, userId);		
		model.addAttribute("enquries",enquries);		
		return "view-filtered-enquries";
	}
}
