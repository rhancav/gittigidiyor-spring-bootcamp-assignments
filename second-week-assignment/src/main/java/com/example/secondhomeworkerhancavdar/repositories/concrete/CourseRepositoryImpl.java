package com.example.secondhomeworkerhancavdar.repositories.concrete;

import com.example.secondhomeworkerhancavdar.entity.Course;
import com.example.secondhomeworkerhancavdar.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    EntityManager entityManager;

    @Autowired
    public CourseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("FROM Course", Course.class).getResultList();
    }

    @Override
    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course save(Course course) {
        return entityManager.merge(course);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(Course course) {
        entityManager.merge(course);
    }
}
