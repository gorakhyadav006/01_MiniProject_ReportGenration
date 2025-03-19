package com.fotp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fotp.entity.UserDtlsEntity;

@Repository
public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {

	public UserDtlsEntity findByEmail(String email);
	
	
	@Query("FROM UserDtlsEntity u WHERE u.email = :email AND u.pwd = :pwd")
	public UserDtlsEntity findByEmailAndPwd(String email, String pwd);

}
