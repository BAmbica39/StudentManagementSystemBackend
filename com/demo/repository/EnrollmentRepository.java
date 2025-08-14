package com.demo.repository;

import com.demo.model.Course;
import com.demo.model.Enrollment;
import com.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> 
{
    List<Enrollment> findByStudent(Student student);
    boolean existsByStudentAndCourse(Student student, Course course);
    void deleteByStudentId(Long studentId);
}