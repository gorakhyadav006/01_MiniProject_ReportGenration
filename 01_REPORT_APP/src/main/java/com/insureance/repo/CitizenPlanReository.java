package com.insureance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insureance.entity.CitizenPlan;

@Repository
public interface CitizenPlanReository extends JpaRepository<CitizenPlan,Integer> {

	@Query("select distinct(planName) from CitizenPlan" )
	public List<String> getPlanName();
	
	@Query("select distinct(planStatus) from CitizenPlan" )
	public List<String> getPlanStatus();
	
	@Query("select distinct(gender) from CitizenPlan" )
	public List<String> getGender();
	
	
}