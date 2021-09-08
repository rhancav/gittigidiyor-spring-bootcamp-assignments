package com.example.schoolmanagement.service;

import com.example.schoolmanagement.entity.GenderStatistics;
import com.example.schoolmanagement.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student> {
    List<GenderStatistics> getGenderStatistics();
}
