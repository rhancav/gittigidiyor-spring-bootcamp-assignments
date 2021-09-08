package dev.sms.service;

import dev.sms.model.Course;
import dev.sms.model.Student;
import dev.sms.repository.CourseRepository;
import dev.sms.utilities.EntityManagerUtilities;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseService implements CourseRepository {
    EntityManager entityManager;
    @Override
    public void save(Course course) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            System.out.println("Successfully created.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }

    }

    @Override
    public void delete(Course course) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Course.class, course.getId()));
            entityManager.getTransaction().commit();
            System.out.println(course + " is successfully deleted.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }

    }

    @Override
    public void delete(long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Student.class, id));
            entityManager.getTransaction().commit();
            System.out.println(id + " is successfully deleted.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }

    }

    @Override
    public void update(Course course, long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            Course fCourse = entityManager.find(Course.class, id);
            fCourse.setCourseCode(course.getCourseCode());
            fCourse.setCreditScore(course.getCreditScore());
            fCourse.setName(course.getName());
            entityManager.merge(fCourse);
            entityManager.getTransaction().commit();
            System.out.println("Successfully updated.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }


    }


    @Override
    public List<Course> findAll() {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        return entityManager.createQuery("from  Course ", Course.class).getResultList();
    }

    @Override
    public Course findById(long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        return entityManager.find(Course.class, id);
    }
}
