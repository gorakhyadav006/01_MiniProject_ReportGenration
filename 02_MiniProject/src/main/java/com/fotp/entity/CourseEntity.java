package com.fotp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "AIT_COURSE_NAME")
@Data
public class CourseEntity {
	
	@Id
	@GeneratedValue
	private Integer courseId;
	private String courseName;
}
