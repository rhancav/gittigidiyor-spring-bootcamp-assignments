package dev.sms.controller;

import dev.sms.model.Student;
import dev.sms.service.StudentService;

import java.util.List;

public class StudentController {
    StudentService studentService = new StudentService();

    public void save(Student student){
        studentService.save(student);
    }

    public List<Student> findAll(){
        return studentService.findAll();
    }

    public void delete(Student student){
        studentService.delete(student);
    }

    public void deleteById(long id){
        studentService.delete(id);
    }

    public Student findById(long id){
        return studentService.findById(id);
    }

    public void update(Student student, long id){
        studentService.update(student,id);
    }
}
