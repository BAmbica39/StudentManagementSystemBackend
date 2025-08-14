package com.demo.service;

import com.demo.dto.EnrollmentDTO;
import java.util.List;

public interface EnrollmentService 
{
    EnrollmentDTO enrollStudent(EnrollmentDTO dto);
    List<EnrollmentDTO> getAllEnrollments();
    List<EnrollmentDTO> getEnrollmentByStudentId(Long studentId);
    EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO dto);
    void deleteEnrollment(Long id);
}