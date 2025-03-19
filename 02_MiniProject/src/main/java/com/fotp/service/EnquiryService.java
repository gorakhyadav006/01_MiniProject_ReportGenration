package com.fotp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fotp.binding.DashboardResponse;
import com.fotp.binding.EnquiryForm;
import com.fotp.binding.EnquirySearchCriteria;
import com.fotp.entity.StudentEnqEntity;

@Service
public interface EnquiryService {
	
	public List<String> getCourses();
	
	public List<String> getEnqStatus();
	
	
	public DashboardResponse getDashboardData(Integer userId);
	
	public Boolean upsertEnquiry(EnquiryForm form);
		
	public List<StudentEnqEntity> getEnquries();
	public List<StudentEnqEntity> getfilteredEnquries(EnquirySearchCriteria criteria, Integer userId);

}
