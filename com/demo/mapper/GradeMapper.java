package com.demo.mapper;

import com.demo.dto.GradeDTO;
import com.demo.model.Grade;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {
	
	
    public Grade toEntity(GradeDTO dto) 
    {
        Grade g = new Grade();
        g.setMarks(dto.getMarks());
        g.setGrade(dto.getGrade());
        return g;
    }

    
    public GradeDTO toDTO(Grade g) 
    {
        GradeDTO dto = new GradeDTO();
        dto.setId(g.getId());
        dto.setStudentId(g.getStudent().getId());
        dto.setCourseId(g.getCourse().getId());
        dto.setEnrollmentId(g.getEnrollment().getId());
        dto.setMarks(g.getMarks());
        dto.setGrade(g.getGrade());
        dto.setStudentName(g.getStudent().getName());
        dto.setCourseTitle(g.getCourse().getName());
        return dto;
    }
    
}
