package com.demo.mapper;

import com.demo.dto.StudentDTO;
import com.demo.model.Student;
import com.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

	// Convert Entity to DTO
    public Student toEntity(StudentDTO dto, User user) 
    {
        Student student = new Student();
        student.setName(dto.getName());
        student.setDob(dto.getDob());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setContact(dto.getContact());
        student.setEmail(dto.getEmail());
        student.setUser(user);
        return student;
    }

    // Convert DTO to Entity
    public StudentDTO toDTO(Student student) 
    {
        StudentDTO dto = new StudentDTO();
	    dto.setId(student.getId()); 
        dto.setName(student.getName());
        dto.setDob(student.getDob());
        dto.setGender(student.getGender());
        dto.setPhone(student.getPhone());
        dto.setAddress(student.getAddress());
        dto.setContact(student.getContact());
        dto.setEmail(student.getEmail());
        dto.setUserId(student.getUser() != null ? student.getUser().getId() : null);
        return dto;
    }
    
}