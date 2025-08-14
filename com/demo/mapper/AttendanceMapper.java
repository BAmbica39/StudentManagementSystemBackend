package com.demo.mapper;

import org.springframework.stereotype.Component;
import com.demo.dto.AttendanceDTO;
import com.demo.model.Attendance;

@Component
public class AttendanceMapper {

	// Convert Entity to DTO
	public AttendanceDTO toDTO(Attendance a) 
	{
        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(a.getId());
        dto.setStudentId(a.getStudent().getId());
        dto.setCourseId(a.getCourse().getId());
        dto.setEnrollmentId(a.getEnrollment().getId());
        dto.setAttendanceDate(a.getAttendanceDate());
        dto.setMarkedDate(a.getMarkedDate());
        dto.setStatus(a.getStatus());
        return dto;
    }

	// Convert DTO to Entity
    public Attendance toEntity(AttendanceDTO dto) 
    {
        Attendance a = new Attendance();
        a.setAttendanceDate(dto.getAttendanceDate());
        a.setMarkedDate(dto.getMarkedDate());
        a.setStatus(dto.getStatus());
        return a;
    }
    
    
}
