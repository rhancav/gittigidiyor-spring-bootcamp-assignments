package com.example.secondhomeworkerhancavdar.controller;

import com.example.secondhomeworkerhancavdar.entity.Course;
import com.example.secondhomeworkerhancavdar.entity.ResponseMessage;
import com.example.secondhomeworkerhancavdar.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Find all courses
    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    // Save
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Course course) {

        return ResponseEntity.ok(new ResponseMessage(courseService.save(course) + " Successfully saved."));
    }

    // Update
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Course course) {
        courseService.update(course);
        return ResponseEntity.ok(new ResponseMessage(course + " Successfully updated."));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (courseService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        courseService.delete(id);
        return ResponseEntity.ok(new ResponseMessage("Successfully deleted."));
    }

    // Find by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        if (courseService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(courseService.findById(id));
    }
}
