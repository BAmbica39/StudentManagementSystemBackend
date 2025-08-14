package com.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EnrollmentDTO {
	
    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDate enrollmentDate;
    
  //Generating Setter And Getter Methods
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
    
    
}
