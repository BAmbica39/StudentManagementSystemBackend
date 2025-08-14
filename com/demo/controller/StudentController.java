package com.demo.controller;

import com.demo.dto.StudentDTO;
import com.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    
    //Only ADMIN or FACULTY can create a student
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PostMapping("/addStudent")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO dto) 
    {
        System.out.println("Entered into StudentController createStudent Method");
        StudentDTO created = studentService.addStudent(dto);
        System.out.println("Returned from StudentController createStudent Method");
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    
    //Any role can get all students (optional: restrict to ADMIN only if needed)
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentDTO>> getAllStudents() 
    {
        System.out.println("Entered into StudentController getAllStudents Method");
        List<StudentDTO> students = studentService.getAllStudents();
        System.out.println("Returned from StudentController getAllStudents Method");
        return ResponseEntity.ok(students);
    }

    
    //STUDENT can view their own info, so this endpoint is open to all roles
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    @GetMapping("/{id}/getStudentById")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) 
    {
        System.out.println("Entered into StudentController getStudentById Method");
        StudentDTO dto = studentService.getStudentById(id);
        System.out.println("Returned from StudentController getStudentById Method");
        return ResponseEntity.ok(dto);
    }
    

    //Only ADMIN or FACULTY can update student
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PutMapping("/{id}/updateStudent")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO dto) 
    {
        System.out.println("Entered into StudentController updateStudent Method");
        StudentDTO updated = studentService.updateStudent(id, dto);
        System.out.println("Returned from StudentController updateStudent Method");
        return ResponseEntity.ok(updated);
    }

    
    //Only ADMIN or FACULTY can delete student
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @DeleteMapping("/{id}/deleteStudent")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) 
    {
        System.out.println("Entered into StudentController deleteStudent Method");
        studentService.deleteStudentById(id);
        System.out.println("Returned from StudentController deleteStudent Method");
        return ResponseEntity.noContent().build();
    }
    
}
