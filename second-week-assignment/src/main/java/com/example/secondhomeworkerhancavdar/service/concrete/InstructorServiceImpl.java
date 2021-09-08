package com.example.secondhomeworkerhancavdar.service.concrete;

import com.example.secondhomeworkerhancavdar.entity.Instructor;
import com.example.secondhomeworkerhancavdar.repositories.InstructorRepository;
import com.example.secondhomeworkerhancavdar.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
    InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(long id) {
        return instructorRepository.findById(id);
    }

    @Override
    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public void delete(long id) {
        instructorRepository.delete(id);
    }

    @Override
    public void update(Instructor instructor) {
        instructorRepository.update(instructor);
    }
}
