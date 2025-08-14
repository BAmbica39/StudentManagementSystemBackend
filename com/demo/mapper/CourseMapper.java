package com.demo.mapper;

import org.springframework.stereotype.Component;
import com.demo.dto.CourseDTO;
import com.demo.model.Course;

@Component
public class CourseMapper {

	// Convert Entity to DTO
    public Course toEntity(CourseDTO dto) 
    {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        return course;
    }

    // Convert DTO to Entity
    public CourseDTO toDTO(Course course) 
    {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setDuration(course.getDuration());
        return dto;
    }
    
}
