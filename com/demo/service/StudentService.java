package com.demo.service;

import com.demo.dto.StudentDTO;
import java.util.List;

public interface StudentService 
{
    StudentDTO addStudent(StudentDTO dto);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(Long id, StudentDTO dto);
    StudentDTO getStudentById(Long id);
    void deleteStudentById(Long id);
}