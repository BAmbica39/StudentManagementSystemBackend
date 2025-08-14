package com.demo.serviceImpl;

import com.demo.dto.CourseDTO;
import com.demo.mapper.CourseMapper;
import com.demo.model.Course;
import com.demo.repository.CourseRepository;
import com.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;
    

    @Override//addCourse
    public CourseDTO addCourse(CourseDTO courseDto) 
    {
        Course course = courseMapper.toEntity(courseDto);
        Course saved = courseRepository.save(course);
        return courseMapper.toDTO(saved);
    }
    

    @Override//getAllCourses
    public List<CourseDTO> getAllCourses() 
    {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    @Override//getCourseById
    public CourseDTO getCourseById(Long id) 
    {
        Course course = courseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.toDTO(course);
    }

    
    @Override//updateCourseById
    public CourseDTO updateCourseById(Long id, CourseDTO courseDto) 
    {
        Course course = courseRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setDuration(courseDto.getDuration());
        Course updated = courseRepository.save(course);
        return courseMapper.toDTO(updated);
    }
    

    @Override//deleteCourseById
    public void deleteCourseById(Long id) 
    {
        courseRepository.deleteById(id);
    }
  
    
}
