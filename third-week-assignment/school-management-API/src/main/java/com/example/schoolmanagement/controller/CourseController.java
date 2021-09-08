package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.entity.Course;
import com.example.schoolmanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable long id) {
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.save(course), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        courseService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody Course course) {
        courseService.update(course);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Course> findByName(@PathVariable String name) {
        return new ResponseEntity<>(courseService.findByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) {
        courseService.deleteByName(name);
    }
}
