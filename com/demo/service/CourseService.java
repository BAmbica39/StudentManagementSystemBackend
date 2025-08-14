package com.demo.service;

import java.util.List;
import com.demo.dto.CourseDTO;

public interface CourseService 
{
    CourseDTO addCourse(CourseDTO courseDto);
    List<CourseDTO> getAllCourses();
    CourseDTO getCourseById(Long id);
    CourseDTO updateCourseById(Long id, CourseDTO courseDto);
    void deleteCourseById(Long id);
}
