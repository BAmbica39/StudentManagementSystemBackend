package com.demo.serviceImpl;

import com.demo.dto.AttendanceDTO;
import com.demo.mapper.AttendanceMapper;
import com.demo.model.*;
import com.demo.repository.*;
import com.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private EnrollmentRepository enrollmentRepository;
    @Autowired private AttendanceMapper attendanceMapper;

    @Override
    public AttendanceDTO markAttendance(AttendanceDTO dto) 
    {
        Student student = studentRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));
        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
        .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Attendance attendance = attendanceMapper.toEntity(dto);
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setEnrollment(enrollment);

        return attendanceMapper.toDTO(attendanceRepository.save(attendance));
    }

    
    @Override
    public List<AttendanceDTO> getAttendanceByStudentId(Long studentId) 
    {
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        return attendanceRepository.findByStudent(student)
        .stream().map(attendanceMapper::toDTO).collect(Collectors.toList());
    }
    

    @Override
    public List<AttendanceDTO> getAttendanceByCourseAndDate(Long courseId, LocalDate date) 
    {
        Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new RuntimeException("Course not found"));
        return attendanceRepository.findByCourseAndAttendanceDate(course, date)
        .stream().map(attendanceMapper::toDTO).collect(Collectors.toList());
    }

    
    @Override
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO dto) 
    {
        Attendance existing = attendanceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance not found"));

        Student student = studentRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new RuntimeException("Student not found"));
        
        Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));
        
        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
        .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        existing.setStudent(student);
        existing.setCourse(course);
        existing.setEnrollment(enrollment);
        existing.setAttendanceDate(dto.getAttendanceDate());
        existing.setMarkedDate(dto.getMarkedDate());
        existing.setStatus(dto.getStatus());

        return attendanceMapper.toDTO(attendanceRepository.save(existing));
    }
    
    @Override
    public AttendanceDTO getAttendanceById(Long id) {
        Attendance attendance = attendanceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance not found with ID: " + id));
        return attendanceMapper.toDTO(attendance);
    }

    
    @Override
    public List<AttendanceDTO> getAllAttendances() 
    {
        return attendanceRepository.findAll()
            .stream()
            .map(attendanceMapper::toDTO)
            .collect(Collectors.toList());
    }

    
    @Override
    public void deleteAttendance(Long id) 
    {
        attendanceRepository.deleteById(id);
    }
    
    
}
