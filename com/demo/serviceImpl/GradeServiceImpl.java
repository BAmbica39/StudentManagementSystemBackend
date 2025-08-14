package com.demo.serviceImpl;

import com.demo.dto.GradeDTO;
import com.demo.mapper.GradeMapper;
import com.demo.model.Course;
import com.demo.model.Enrollment;
import com.demo.model.Grade;
import com.demo.model.Student;
import com.demo.repository.CourseRepository;
import com.demo.repository.EnrollmentRepository;
import com.demo.repository.GradeRepository;
import com.demo.repository.StudentRepository;
import com.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public GradeDTO addGrade(GradeDTO dto) 
    {
        Student student = studentRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + dto.getStudentId()));

        Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found with id: " + dto.getCourseId()));

        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
        .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + dto.getEnrollmentId()));

        Grade grade = gradeMapper.toEntity(dto);
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setEnrollment(enrollment);

        Grade saved = gradeRepository.save(grade);
        return gradeMapper.toDTO(saved);
    }

    
    @Override
    public List<GradeDTO> getAllGrades() 
    {
        return gradeRepository.findAll()
                .stream()
                .map(gradeMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    @Override
    public List<GradeDTO> getGradesByStudentId(Long studentId) 
    {
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        return gradeRepository.findByStudent(student)
                .stream()
                .map(gradeMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    @Override
    public GradeDTO getGradeByStudentAndCourse(Long studentId, Long courseId) 
    {
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        Grade grade = gradeRepository.findByStudentAndCourse(student, course);
        if (grade == null) throw new RuntimeException("Grade not found for this student and course.");

        return gradeMapper.toDTO(grade);
    }

    
    @Override
    public GradeDTO updateGrade(GradeDTO dto) 
    {
        Grade existing = gradeRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Grade not found with id: " + dto.getId()));

        Student student = studentRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new RuntimeException("Student not found with id: " + dto.getStudentId()));

        Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found with id: " + dto.getCourseId()));

        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
        .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + dto.getEnrollmentId()));

        existing.setStudent(student);
        existing.setCourse(course);
        existing.setEnrollment(enrollment);
        existing.setGrade(dto.getGrade());
        existing.setMarks(dto.getMarks());

        Grade saved = gradeRepository.save(existing);
        return gradeMapper.toDTO(saved);
    }

    
    @Override
    public void deleteGrade(Long id) 
    {
        if (!gradeRepository.existsById(id)) 
        {
            throw new RuntimeException("Grade not found with id: " + id);
        }
        gradeRepository.deleteById(id);
    }

    
    @Override
    public GradeDTO getGradeById(Long id) 
    {
        Grade grade = gradeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
        return gradeMapper.toDTO(grade);
    }
    
}
