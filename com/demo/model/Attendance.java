package com.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq")
    @SequenceGenerator(name = "attendance_seq", sequenceName = "ATTENDANCE_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @Column(name = "date")
    private LocalDateTime markedDate;

    private String status;
    
    
   //Generating Setter And Getter Methods
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
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
