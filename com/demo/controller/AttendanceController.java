package com.demo.controller;

import com.demo.dto.AttendanceDTO;
import com.demo.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    

    //Only ADMIN or FACULTY can mark attendance
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PostMapping("/markAttendance")
    public ResponseEntity<AttendanceDTO> markAttendance(@Valid @RequestBody AttendanceDTO dto) 
    {
        return ResponseEntity.ok(attendanceService.markAttendance(dto));
    }

    
    //STUDENT can only access their own attendance
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getByStudent(@PathVariable Long studentId) 
    {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudentId(studentId));
    }

    
    //Only ADMIN/FACULTY can get by course and date
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AttendanceDTO>> getByCourseAndDate(@PathVariable Long courseId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) 
    {
        return ResponseEntity.ok(attendanceService.getAttendanceByCourseAndDate(courseId, date));
    }

    
    //Only ADMIN/FACULTY can get all attendances
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @GetMapping("/getAllAttendances")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() 
    {
        return ResponseEntity.ok(attendanceService.getAllAttendances());
    }

    
    //Only ADMIN/FACULTY can update
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PutMapping("/update/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable Long id, @Valid @RequestBody AttendanceDTO dto) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, dto));
    }
    
    
    //Ensure only valid roles can access
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')") 
    @GetMapping("/getById/{id}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable Long id) 
    {
        return ResponseEntity.ok(attendanceService.getAttendanceById(id));
    }


    //Only ADMIN/FACULTY can delete
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) 
    {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
    
}
