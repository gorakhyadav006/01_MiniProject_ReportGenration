package com.fotp.runners;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fotp.entity.CourseEntity;
import com.fotp.entity.EnqStatusEntity;
import com.fotp.repo.CourseRepo;
import com.fotp.repo.EnqStatusRepo;

@Component
public class DataLoader  implements ApplicationRunner{

		@Autowired
		CourseRepo courseRepo;
		
		@Autowired
		EnqStatusRepo enqStRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//inserting coursName
		courseRepo.deleteAll();		
		CourseEntity c1 = new CourseEntity();	
		c1.setCourseName("JAVA");
		CourseEntity c2 = new CourseEntity();	
		c2.setCourseName(".NET");
		CourseEntity c3 = new CourseEntity();	
		c3.setCourseName("DEVOPS");
		CourseEntity c4 = new CourseEntity();	
		c4.setCourseName("JAVASCRIPT");
		
		courseRepo.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		//inserting Status details
		enqStRepo.deleteAll();	
		
		EnqStatusEntity e1 = new EnqStatusEntity();	
		e1.setStatusName("NEW");
		EnqStatusEntity e2 = new EnqStatusEntity();	
		e2.setStatusName("ENROLLED");
		EnqStatusEntity e3 = new EnqStatusEntity();	
		e3.setStatusName("LOST");	
		enqStRepo.saveAll(Arrays.asList(e1,e2,e3));
		
	}
}
