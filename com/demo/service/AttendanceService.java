package com.demo.service;

import java.time.LocalDate;
import java.util.List;
import com.demo.dto.AttendanceDTO;

public interface AttendanceService 
{
    AttendanceDTO markAttendance(AttendanceDTO dto);
    List<AttendanceDTO> getAttendanceByStudentId(Long studentId);
    List<AttendanceDTO> getAttendanceByCourseAndDate(Long courseId, LocalDate date);
    AttendanceDTO updateAttendance(Long id, AttendanceDTO dto);
    List<AttendanceDTO> getAllAttendances();
    void deleteAttendance(Long id);
    AttendanceDTO getAttendanceById(Long id);

}

