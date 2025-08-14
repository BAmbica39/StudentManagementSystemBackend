package com.demo.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.Attendance;
import com.demo.model.Course;
import com.demo.model.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> 
{
    List<Attendance> findByStudent(Student student);
    List<Attendance> findByCourseAndAttendanceDate(Course course, LocalDate date);
    void deleteByStudentId(Long studentId);

}
