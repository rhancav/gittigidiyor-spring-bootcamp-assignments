package dev.sms.service;

import dev.sms.model.Student;
import dev.sms.repository.StudentRepository;
import dev.sms.utilities.EntityManagerUtilities;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService implements StudentRepository {
    EntityManager entityManager;
    @Override
    public void save(Student student) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try{

            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            System.out.println("Student successfully saved: "+student);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }

    }

    @Override
    public void delete(Student student) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Student.class,student.getId()));
            entityManager.getTransaction().commit();
            System.out.println(student+" is successfully deleted.");
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }

    }

    @Override
    public void delete(long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Student.class,id));
            entityManager.getTransaction().commit();
            System.out.println("Successfully deleted.");
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }
    }

    @Override
    public void update(Student student, long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try{
            entityManager.getTransaction().begin();
            Student fStudent = entityManager.find(Student.class, id);
            fStudent.setName(student.getName());
            fStudent.setAddress(student.getAddress());
            fStudent.setBirthday(student.getBirthday());
            fStudent.setCourses(student.getCourses());
            fStudent.setGender(student.getGender());
            entityManager.merge(fStudent);
            entityManager.getTransaction().commit();
            System.out.println("Successfully updated.");
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }

    }

    @Override
    public List<Student> findAll() {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        return entityManager.createQuery("from  Student ", Student.class).getResultList();
    }

    @Override
    public Student findById(long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        return entityManager.find(Student.class, id);

    }
}
