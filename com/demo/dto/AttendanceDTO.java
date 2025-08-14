package com.demo.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AttendanceDTO {
	
    private Long id;
    private Long studentId;
    private Long courseId;
    private Long enrollmentId;
    private LocalDate attendanceDate;
    private LocalDateTime markedDate;
    private String status; // "Present" or "Absent"
    
    
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
	public Long getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public LocalDateTime getMarkedDate() {
		return markedDate;
	}
	public void setMarkedDate(LocalDateTime markedDate) {
		this.markedDate = markedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
   
}
