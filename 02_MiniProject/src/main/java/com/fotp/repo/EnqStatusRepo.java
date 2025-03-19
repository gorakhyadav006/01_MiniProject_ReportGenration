package com.fotp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fotp.entity.EnqStatusEntity;
import com.fotp.entity.StudentEnqEntity;

@Repository
public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity,Integer> {



}
