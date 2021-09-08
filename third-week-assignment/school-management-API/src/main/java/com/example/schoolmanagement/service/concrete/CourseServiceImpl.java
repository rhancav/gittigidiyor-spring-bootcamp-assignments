package com.example.schoolmanagement.service.concrete;

import com.example.schoolmanagement.entity.Course;
import com.example.schoolmanagement.exceptions.EntityAlreadyExists;
import com.example.schoolmanagement.exceptions.EntityNotFoundException;
import com.example.schoolmanagement.repository.CourseRepository;
import com.example.schoolmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {
    CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    @Override
    public Course findById(long id) {
        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found."));
    }

    @Override
    @Transactional
    public Course save(Course course) {
        if (courseRepository.existsById(course.getId())) {
            throw new EntityAlreadyExists("Course already exists.");
        }
        return courseRepository.save(course);
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findCourseByName(name).orElseThrow(() -> new EntityNotFoundException("Course not found."));
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        if (courseRepository.findCourseByName(name).isEmpty()) {
            throw new EntityNotFoundException("Course not found.");
        }
        courseRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course not found.");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public void delete(Course course) {
        if (!courseRepository.existsById(course.getId())) {
            throw new EntityNotFoundException("Course not found.");
        }
        courseRepository.delete(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        if (courseRepository.existsById(course.getId())) {
            courseRepository.save(course);
        }
        throw new EntityNotFoundException("Course not found.");
    }
}
