package com.example.secondhomeworkerhancavdar.service.concrete;

import com.example.secondhomeworkerhancavdar.entity.Student;
import com.example.secondhomeworkerhancavdar.repositories.StudentRepository;
import com.example.secondhomeworkerhancavdar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(long id) {
        studentRepository.delete(id);
    }

    @Override
    public void update(Student student) {
        studentRepository.update(student);
    }
}
