package com.fotp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fotp.entity.CourseEntity;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity,Integer> {

}
