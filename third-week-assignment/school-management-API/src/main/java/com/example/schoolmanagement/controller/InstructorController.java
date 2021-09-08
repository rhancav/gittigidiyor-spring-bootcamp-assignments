package com.example.schoolmanagement.controller;

import com.example.schoolmanagement.entity.Instructor;
import com.example.schoolmanagement.entity.InstructorsByType;
import com.example.schoolmanagement.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> findAll() {
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> findById(@PathVariable long id) {
        return new ResponseEntity<>(instructorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Instructor> save(@RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.save(instructor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        instructorService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody Instructor instructor) {
        instructorService.update(instructor);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Instructor> findByName(@PathVariable String name) {
        return new ResponseEntity<>(instructorService.findByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) {
        instructorService.deleteByName(name);
    }

    @GetMapping("/highest-paid")
    public ResponseEntity<InstructorsByType> getTopEarners() {
        InstructorsByType instructorsByType = new InstructorsByType(instructorService.getTopEarningVisitingResearchers(), instructorService.getTopEarningPermanentInstructors());
        return ResponseEntity.ok(instructorsByType);
    }

    @GetMapping("/lowest-paid")
    public ResponseEntity<InstructorsByType> getLowestPaid() {
        InstructorsByType instructorsByType = new InstructorsByType(instructorService.getLowestPayedVisitingResearchers(), instructorService.getLowestPayedPermanentInstructors());
        return ResponseEntity.ok(instructorsByType);
    }
}
