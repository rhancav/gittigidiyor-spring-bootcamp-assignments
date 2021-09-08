package com.example.secondhomeworkerhancavdar.controller;

import com.example.secondhomeworkerhancavdar.entity.Instructor;
import com.example.secondhomeworkerhancavdar.entity.ResponseMessage;
import com.example.secondhomeworkerhancavdar.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instructors")
public class InstructorController {
    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    // Find all the students
    @GetMapping
    public ResponseEntity<List<Instructor>> findAll() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    /**
     * Need to specify "type" attribute while forming the JSON body.
     * It has two types as expected(can be changed ofc, see Instructor.class) PermanentInstructor
     * and VisitingResearcher. Not sure if this is the best approach.
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Instructor instructor) {

        return ResponseEntity.ok(new ResponseMessage(instructorService.save(instructor) + " Successfully saved."));
    }

    // Update
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Instructor instructor) {

        instructorService.update(instructor);
        return new ResponseEntity<>(new ResponseMessage("Successfully updated."), HttpStatus.OK);


    }

    // Delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (instructorService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        instructorService.delete(id);
        return ResponseEntity.ok(new ResponseMessage("Successfully deleted."));
    }

    // Find by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        if (instructorService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(instructorService.findById(id));
    }
}
