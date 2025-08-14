package com.demo.controller;

import com.demo.dto.CourseDTO;
import com.demo.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    
    
    @PostMapping("/addcourse")//Add a new course
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO courseDto) 
    {
        CourseDTO saved = courseService.addCourse(courseDto);
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/getAllCourses")//Get all courses
    public ResponseEntity<List<CourseDTO>> getAllCourses() 
    {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    
    @GetMapping("/{id}/getCourseById") //Get course by ID
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) 
    {
        CourseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    
    @PutMapping("/{id}/updateCourse") //Update course
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id,@Valid @RequestBody CourseDTO courseDto) 
    {
        CourseDTO updated = courseService.updateCourseById(id, courseDto);
        return ResponseEntity.ok(updated);
    }

    
    @DeleteMapping("/{id}/deleteCourse") //Delete course
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) 
    {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }
  
    
}
