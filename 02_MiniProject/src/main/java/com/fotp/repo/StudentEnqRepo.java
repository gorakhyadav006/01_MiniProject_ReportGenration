package com.fotp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fotp.entity.StudentEnqEntity;

@Repository
public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer> {

}
