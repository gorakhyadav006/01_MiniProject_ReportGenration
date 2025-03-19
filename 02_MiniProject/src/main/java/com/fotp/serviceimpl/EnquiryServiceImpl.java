package com.fotp.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fotp.binding.DashboardResponse;
import com.fotp.binding.EnquiryForm;
import com.fotp.binding.EnquirySearchCriteria;
import com.fotp.entity.CourseEntity;
import com.fotp.entity.EnqStatusEntity;
import com.fotp.entity.StudentEnqEntity;
import com.fotp.entity.UserDtlsEntity;
import com.fotp.repo.CourseRepo;
import com.fotp.repo.EnqStatusRepo;
import com.fotp.repo.StudentEnqRepo;
import com.fotp.repo.UserDtlsRepo;
import com.fotp.service.EnquiryService;


@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private StudentEnqRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo enqRepo;
	
	@Autowired
	private UserDtlsRepo user;
	
	@Override
	public List<String> getCourses() {
		List<CourseEntity> all = courseRepo.findAll();
		List<String> coursesName = new ArrayList<String>();
		for(CourseEntity name:all) {
			coursesName.add(name.getCourseName());
		}		
		return coursesName;
	}

	@Override
	public List<String> getEnqStatus() {
		List<EnqStatusEntity> all = enqRepo.findAll();
		List<String> enqStatus = new ArrayList<String>();
		for(EnqStatusEntity name:all) {
			enqStatus.add(name.getStatusName());
		}		
		return enqStatus;
	}

	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		
		DashboardResponse dashboardResponse = new DashboardResponse();
		Optional<UserDtlsEntity> findbyId = userDtlsRepo.findById(userId);
		
		if(findbyId.isPresent()) {
			UserDtlsEntity userEntity = findbyId.get();
			List<StudentEnqEntity> enquiries = userEntity.getEnquiries();
			
			Integer total = enquiries.size();
			
			List<StudentEnqEntity> enrolled = enquiries.stream()
					 .filter(e->e.getEnqStatus().equals("ENROLLED"))
					 .collect(Collectors.toList());
			
			Integer enrolledCnt = enrolled.size();
			
			List<StudentEnqEntity> lost = enquiries.stream()
										.filter(e->e.getEnqStatus().equals("LOST"))
										.collect(Collectors.toList());
			
			Integer lostCnt = lost.size();
			
			dashboardResponse.setTotalEnquriesCnt(total);
			dashboardResponse.setEnrolledCnt(enrolledCnt);
			dashboardResponse.setLostCnt(lostCnt);
		} 
		return dashboardResponse;
	}

	@Override
	public Boolean upsertEnquiry(EnquiryForm form) {
	
		StudentEnqEntity enqEntity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);		
		
		Integer sId = (Integer)session.getAttribute("userId");
		UserDtlsEntity userEntity = userDtlsRepo.findById(sId).get();
		
		enqEntity.setUser(userEntity);
		
		studentRepo.save(enqEntity);  
		
		return true;
	}

	@Override
	public List<StudentEnqEntity> getEnquries(){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Optional<UserDtlsEntity> byId = userDtlsRepo.findById(userId);
		
		if(byId.isPresent()) {
			UserDtlsEntity userDtlsEntity = byId.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			return enquiries;
		}		
		return null;
	}

	

	@Override
	public List<StudentEnqEntity> getfilteredEnquries(EnquirySearchCriteria criteria, Integer userId) {
		
		Optional<UserDtlsEntity> byId = userDtlsRepo.findById(userId);
		
		if(byId.isPresent()) {
			UserDtlsEntity userDtlsEntity = byId.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			
			//filter login by JAVA8
			if(null!=criteria.getEnqStatus() && !"".equals(criteria.getEnqStatus())) {
				  enquiries = enquiries.stream()
				.filter(e->(e.getEnqStatus()
						.equals(criteria.getEnqStatus())))
						.collect(Collectors.toList());
			}
			if(null!=criteria.getClassMode() && !"".equals(criteria.getClassMode())) {
				  enquiries = enquiries.stream()
				.filter(e->(e.getClassMode()
						.equals(criteria.getClassMode())))
						.collect(Collectors.toList());
			}
			if(null!=criteria.getCourse() && !"".equals(criteria.getCourse())) {
				  enquiries = enquiries.stream()
				.filter(e->(e.getCourseName()
						.equals(criteria.getCourse())))
						.collect(Collectors.toList());
			}			
			return enquiries;
		}		
		return null;
	}

}
