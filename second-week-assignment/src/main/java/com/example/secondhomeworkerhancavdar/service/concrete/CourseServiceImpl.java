package com.example.secondhomeworkerhancavdar.service.concrete;

import com.example.secondhomeworkerhancavdar.entity.Course;
import com.example.secondhomeworkerhancavdar.repositories.CourseRepository;
import com.example.secondhomeworkerhancavdar.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delete(long id) {
        courseRepository.delete(id);
    }

    @Override
    public void update(Course course) {
        courseRepository.update(course);
    }
}
