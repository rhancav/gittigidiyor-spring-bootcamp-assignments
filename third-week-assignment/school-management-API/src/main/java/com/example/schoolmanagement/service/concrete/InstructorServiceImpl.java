package com.example.schoolmanagement.service.concrete;

import com.example.schoolmanagement.entity.Instructor;
import com.example.schoolmanagement.entity.PermanentInstructor;
import com.example.schoolmanagement.entity.VisitingResearcher;
import com.example.schoolmanagement.exceptions.EntityAlreadyExists;
import com.example.schoolmanagement.exceptions.EntityNotFoundException;
import com.example.schoolmanagement.repository.InstructorRepository;
import com.example.schoolmanagement.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class InstructorServiceImpl implements InstructorService {
    InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> findAll() {
        List<Instructor> instructors = new ArrayList<>();
        instructorRepository.findAll().forEach(instructors::add);
        return instructors;
    }

    @Override
    public Instructor findById(long id) {
        if (!instructorRepository.existsById(id)) {
            throw new EntityNotFoundException("Instructor not found.");
        }

        return instructorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

    @Override
    @Transactional
    public Instructor save(Instructor instructor) {
        if (instructorRepository.existsById(instructor.getId())) {
            throw new EntityAlreadyExists("Instructor already exists");
        }
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor findByName(String name) {
        return instructorRepository.findInstructorByName(name).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        if (instructorRepository.findInstructorByName(name).isEmpty()) {
            throw new EntityNotFoundException("Instructor not found.");
        }
        instructorRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!instructorRepository.existsById(id)) {
            throw new EntityNotFoundException("Instructor not found.");
        }
        instructorRepository.deleteById(id);
    }

    @Override
    public void delete(Instructor instructor) {
        if (!instructorRepository.existsById(instructor.getId())) {
            throw new EntityNotFoundException("Instructor not found.");
        }
        instructorRepository.delete(instructor);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        if (!instructorRepository.existsById(instructor.getId())) {
            throw new EntityNotFoundException("Instructor not found.");
        }
        instructorRepository.save(instructor);
    }

    public List<PermanentInstructor> getTopEarningPermanentInstructors() {
        return instructorRepository.highestPayedPermanentInstructors();
    }

    @Override
    public List<VisitingResearcher> getTopEarningVisitingResearchers() {
        return instructorRepository.highestPayedVisitingResearchers();
    }

    @Override
    public List<PermanentInstructor> getLowestPayedPermanentInstructors() {
        return instructorRepository.lowestPayedPermanentInstructors();
    }

    @Override
    public List<VisitingResearcher> getLowestPayedVisitingResearchers() {
        return instructorRepository.lowestPayedVisitingResearchers();
    }
}
