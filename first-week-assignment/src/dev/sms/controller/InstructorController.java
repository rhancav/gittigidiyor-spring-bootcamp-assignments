package dev.sms.controller;

import dev.sms.model.Instructor;
import dev.sms.service.InstructorService;

import java.util.List;

public class InstructorController {
    InstructorService instructorService = new InstructorService();

    public void save(Instructor instructor) {
        instructorService.save(instructor);
    }

    public void delete(Instructor instructor) {
        instructorService.delete(instructor);
    }

    public void delete(long id) {
        instructorService.delete(id);
    }

    public List<Instructor> findAll() {
        return instructorService.findAll();
    }

    public Instructor findById(long id) {
        return instructorService.findById(id);
    }

    public void update(Instructor instructor, long id) {
        instructorService.update(instructor, id);
    }
}
