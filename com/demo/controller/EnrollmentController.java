package com.demo.controller;

import com.demo.dto.EnrollmentDTO;
import com.demo.model.Enrollment;
import com.demo.repository.EnrollmentRepository;
import com.demo.service.EnrollmentService;
import com.demo.mapper.EnrollmentMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentMapper enrollmentMapper;
    

    //Admin or Faculty can enroll students
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PostMapping("/enrollStudent")
    public ResponseEntity<EnrollmentDTO> enrollStudent(@Valid @RequestBody EnrollmentDTO dto) 
    {
        return ResponseEntity.ok(enrollmentService.enrollStudent(dto));
    }

    
    //Admin or Faculty can view all enrollments
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @GetMapping("/getAllEnrollments")
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() 
    {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    
    //Student can view their own enrollments
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentByStudentId(studentId));
    }

    //Admin or Faculty can get enrollment by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @GetMapping("/getEnrollment/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) 
    {
        Enrollment enrollment = enrollmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return ResponseEntity.ok(enrollmentMapper.toDTO(enrollment));
    }

    
    //Admin or Faculty can update enrollment
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PutMapping("/updateEnrollment/{id}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable Long id, @Valid @RequestBody EnrollmentDTO dto) 
    {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(id, dto));
    }

    
    //Admin or Faculty can delete enrollment
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @DeleteMapping("/deleteEnrollment/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) 
    {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok("Enrollment deleted successfully");
    }
    
}
