package com.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.Course;
import com.demo.model.Grade;
import com.demo.model.Student;

public interface GradeRepository extends JpaRepository<Grade, Long> 
{
	List<Grade>findByStudent(Student student);
	Grade findByStudentAndCourse(Student student,Course course);
	void deleteByStudentId(Long studentId);


}
