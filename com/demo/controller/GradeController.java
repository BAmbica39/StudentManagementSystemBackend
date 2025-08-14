package com.demo.controller;

import com.demo.dto.GradeDTO;
import com.demo.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    //Only ADMIN or FACULTY can add grade
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PostMapping("/addGrade")
    public ResponseEntity<GradeDTO> addGrade(@Valid @RequestBody GradeDTO dto) 
    {
        return ResponseEntity.ok(gradeService.addGrade(dto));
    }

    
    //Only ADMIN or FACULTY can view all grades
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @GetMapping("/getAllGrades")
    public ResponseEntity<List<GradeDTO>> getAllGrades() 
    {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }
    

    //Accessible by ADMIN, FACULTY, and STUDENT
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    @GetMapping("/getGrade/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable Long id) 
    {
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }

    
    //STUDENT can view their own grades
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeDTO>> getGradesByStudentId(@PathVariable Long studentId) 
    {
        return ResponseEntity.ok(gradeService.getGradesByStudentId(studentId));
    }

    
    //STUDENT can view their grade for specific course
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<GradeDTO> getGradeByStudentAndCourse(@PathVariable Long studentId,@PathVariable Long courseId) 
    {
        return ResponseEntity.ok(gradeService.getGradeByStudentAndCourse(studentId, courseId));
    }
    

    //Only ADMIN or FACULTY can update grades
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PutMapping("/updateGrade/{id}")
    public ResponseEntity<GradeDTO> updateGrade(@PathVariable Long id, @Valid @RequestBody GradeDTO dto) 
    {
        dto.setId(id);
        return ResponseEntity.ok(gradeService.updateGrade(dto));
    }

    
    //Only ADMIN or FACULTY can delete grades
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @DeleteMapping("/deleteGrade/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) 
    {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
    
}
