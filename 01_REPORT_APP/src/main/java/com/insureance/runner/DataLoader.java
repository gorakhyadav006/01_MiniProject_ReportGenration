package com.insureance.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.insureance.entity.CitizenPlan;
import com.insureance.repo.CitizenPlanReository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanReository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//Cash Plan Data 
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("Jhony");
		c1.setGender("Male");
		c1.setPlanName("Cash");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(50000.00);
				
		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Smithy");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denied");
		c2.setPlanStartDate(LocalDate.now());
		c2.setPlanEndDate(LocalDate.now().plusMonths(6));
		c2.setDenialReason("Rental Income");
				
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("DogCathy");
		c3.setGender("Fe-Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmt(50000.00);
		c3.setTerminatedDate(LocalDate.now().plusWeeks(8));
		c3.setTerminationRsn( "Employed");
		
		
		//Food Plan Data 
				CitizenPlan c4 = new CitizenPlan();
				c4.setCitizenName("Rameshwar");
				c4.setGender("Male");
				c4.setPlanName("Food");
				c4.setPlanStatus("Approved");
				c4.setPlanStartDate(LocalDate.now());
				c4.setPlanEndDate(LocalDate.now().plusMonths(6));
				c4.setBenefitAmt(40000.00);
						
				CitizenPlan c5 = new CitizenPlan();
				c5.setCitizenName("Radhika");
				c5.setGender("Fe-Male");
				c5.setPlanName("Food");
				c5.setPlanStatus("Denied");
				c5.setPlanStartDate(LocalDate.now());
				c5.setPlanEndDate(LocalDate.now().plusMonths(6));
				c5.setDenialReason("Properties Income");
						
				CitizenPlan c6 = new CitizenPlan();
				c6.setCitizenName("Laxman");
				c6.setGender("Male");
				c6.setPlanName("Food");
				c6.setPlanStatus("Terminated");
				c6.setPlanStartDate(LocalDate.now().minusMonths(4));
				c6.setPlanEndDate(LocalDate.now().plusMonths(6));
				c6.setBenefitAmt(50000.00);
				c6.setTerminatedDate(LocalDate.now().plusDays(67));
				c6.setTerminationRsn( "Employed");
				
			//Medical plan Data 
				
				CitizenPlan c7 = new CitizenPlan();
				c7.setCitizenName("Jamesh");
				c7.setGender("Male");
				c7.setPlanName("Medical");
				c7.setPlanStatus("Approved");
				c7.setPlanStartDate(LocalDate.now());
				c7.setPlanEndDate(LocalDate.now().plusMonths(6));
				c7.setBenefitAmt(30000.00);
						
				CitizenPlan c8 = new CitizenPlan();
				c8.setCitizenName("Samm");
				c8.setGender("Male");
				c8.setPlanName("Medical");
				c8.setPlanStatus("Denied");
				c8.setPlanStartDate(LocalDate.now());
				c8.setPlanEndDate(LocalDate.now().plusMonths(6));
				c8.setDenialReason("Rental Income");
						
				CitizenPlan c9 = new CitizenPlan();
				c9.setCitizenName("Laxmina");
				c9.setGender("Fe-Male");
				c9.setPlanName("Medical");
				c9.setPlanStatus("Terminated");
				c9.setPlanStartDate(LocalDate.now().minusMonths(4));
				c9.setPlanEndDate(LocalDate.now().plusMonths(6));
				c9.setBenefitAmt(60000.00);
				c9.setTerminatedDate(LocalDate.now().plusDays(5));
				c9.setTerminationRsn( "Govt. Job");
				
				
				//Employment plan 
				
				CitizenPlan c10 = new CitizenPlan();
				c10.setCitizenName("Rajat");
				c10.setGender("Male");
				c10.setPlanName("Employment");
				c10.setPlanStatus("Approved");
				c10.setPlanStartDate(LocalDate.now());
				c10.setPlanEndDate(LocalDate.now().plusMonths(6));
				c10.setBenefitAmt(40000.00);
						
				CitizenPlan c11 = new CitizenPlan();
				c11.setCitizenName("Rankita");
				c11.setGender("Fe-Male");
				c11.setPlanName("Employment");
				c11.setPlanStatus("Denied");
				c11.setPlanStartDate(LocalDate.now());
				c11.setPlanEndDate(LocalDate.now().plusMonths(6));
				c11.setDenialReason("Properties Income");
						
				CitizenPlan c12 = new CitizenPlan();
				c12.setCitizenName("Gibbess");
				c12.setGender("Male");
				c12.setPlanName("Employment");
				c12.setPlanStatus("Terminated");
				c12.setPlanStartDate(LocalDate.now().minusMonths(4));
				c12.setPlanEndDate(LocalDate.now().plusMonths(6));
				c12.setBenefitAmt(50000.00);
				c12.setTerminatedDate(LocalDate.now().plusMonths(1));
				c12.setTerminationRsn( "Employed");
				
				System.out.println("All object Created succesffully");
				List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
				//repo.saveAll(list);
	}

}
