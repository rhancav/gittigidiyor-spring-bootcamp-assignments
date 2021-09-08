package dev.sms.controller;

import dev.sms.model.Course;
import dev.sms.service.CourseService;

import java.util.List;

public class CourseController {
    CourseService courseService = new CourseService();

    public void save(Course course) {
        courseService.save(course);
    }

    public void delete(Course course) {
        courseService.delete(course);
    }

    public void deleteById(long id) {
        courseService.delete(id);
    }

    public void update(Course course, long id) {
        courseService.update(course, id);
    }

    public List<Course> findAll() {
        return courseService.findAll();
    }

    public Course findById(long id) {
        return courseService.findById(id);
    }

}
