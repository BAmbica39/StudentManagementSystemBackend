package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
