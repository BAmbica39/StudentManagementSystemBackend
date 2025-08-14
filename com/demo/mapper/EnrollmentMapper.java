package com.demo.mapper;

import com.demo.dto.EnrollmentDTO;
import com.demo.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {
	
	// Convert Entity to DTO
    public EnrollmentDTO toDTO(Enrollment e) 
    {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(e.getId());
        dto.setStudentId(e.getStudent().getId());
        dto.setCourseId(e.getCourse().getId());
        dto.setEnrollmentDate(e.getEnrollmentDate());
        return dto;
    }

    // Convert DTO to Entity
    public Enrollment toEntity(EnrollmentDTO dto) 
    {
        Enrollment e = new Enrollment();
        e.setEnrollmentDate(dto.getEnrollmentDate());
        return e;
    }
    
}
