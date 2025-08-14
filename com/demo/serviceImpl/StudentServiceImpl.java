package com.demo.serviceImpl;

import com.demo.dto.StudentDTO;
import com.demo.mapper.StudentMapper;
import com.demo.model.Student;
import com.demo.model.User;
import com.demo.repository.*;
import com.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired private StudentRepository studentRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private GradeRepository gradeRepository;
    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private EnrollmentRepository enrollmentRepository;
    @Autowired private StudentMapper studentMapper;

    //Add Student Method
    @Override
    public StudentDTO addStudent(StudentDTO dto) 
    {
        User user = userRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        Student student = studentMapper.toEntity(dto, user);
        Student saved = studentRepository.save(student);
        return studentMapper.toDTO(saved);
    }

    
    //Get All Students Method
    @Override
    public List<StudentDTO> getAllStudents() 
    {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    
    //Update Student Method
    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) 
    {
        Student existing = studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(dto.getName());
        existing.setDob(dto.getDob());
        existing.setGender(dto.getGender());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());
        existing.setContact(dto.getContact());
        existing.setEmail(dto.getEmail());

        Student updated = studentRepository.save(existing);
        return studentMapper.toDTO(updated);
    }

    
    //Get Student by ID Method
    @Override
    public StudentDTO getStudentById(Long id) 
    {
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDTO(student);
    }

    
    //Delete Student by ID Method with related records cleanup
    @Override
    public void deleteStudentById(Long id) 
    {
        // First delete related child records to avoid foreign key constraint errors
        gradeRepository.deleteByStudentId(id);
        attendanceRepository.deleteByStudentId(id);
        enrollmentRepository.deleteByStudentId(id);

        // Now delete the student
        studentRepository.deleteById(id);
    }
    
}
