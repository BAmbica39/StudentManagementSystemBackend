package com.demo.serviceImpl;

import com.demo.dto.EnrollmentDTO;
import com.demo.mapper.EnrollmentMapper;
import com.demo.model.Course;
import com.demo.model.Enrollment;
import com.demo.model.Student;
import com.demo.repository.CourseRepository;
import com.demo.repository.EnrollmentRepository;
import com.demo.repository.StudentRepository;
import com.demo.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentMapper enrollmentMapper;
    

    //Enroll a student
    @Override
    public EnrollmentDTO enrollStudent(EnrollmentDTO dto) 
    {
        Student student = studentRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());

        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(saved);
    }
    

    //Get all enrollments
    @Override
    public List<EnrollmentDTO> getAllEnrollments() 
    {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toDTO)
                .collect(Collectors.toList());
    }
    

    //Get enrollments by student ID
    @Override
    public List<EnrollmentDTO> getEnrollmentByStudentId(Long studentId) 
    {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return enrollmentRepository.findByStudent(student)
                .stream()
                .map(enrollmentMapper::toDTO)
                .collect(Collectors.toList());
    }


    //Update enrollment
    @Override
    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO dto) 
    {
        Enrollment enrollment = enrollmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Student student = studentRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());

        Enrollment updated = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDTO(updated);
    }

    
    //Delete enrollment
    @Override
    public void deleteEnrollment(Long id) 
    {
        Enrollment enrollment = enrollmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollmentRepository.delete(enrollment);
    }
    
    
}
