package dev.sms.service;

import dev.sms.model.Instructor;
import dev.sms.model.PermanentInstructor;
import dev.sms.model.VisitingResearcher;
import dev.sms.repository.InstructorRepository;
import dev.sms.utilities.EntityManagerUtilities;

import javax.persistence.EntityManager;
import java.util.List;

public class InstructorService implements InstructorRepository {
    EntityManager entityManager;

    @Override
    public void save(Instructor instructor) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
            System.out.println("Instructor successfully saved: " + instructor);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }


    }

    @Override
    public void delete(Instructor instructor) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Instructor.class, instructor.getId()));
            entityManager.getTransaction().commit();
            System.out.println(instructor + " is successfully deleted.");
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
            entityManager.remove(entityManager.find(Instructor.class, id));
            entityManager.getTransaction().commit();
            System.out.println("Successfully deleted.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtilities.closeEntityManager(entityManager);
        }

    }

    @Override
    public void update(Instructor instructor, long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        try {
            entityManager.getTransaction().begin();
            Instructor fInstructor = entityManager.find(Instructor.class, id);
            if (instructor instanceof PermanentInstructor && fInstructor instanceof PermanentInstructor) {
                ((PermanentInstructor) fInstructor).setFixedSalary(((PermanentInstructor) instructor).getFixedSalary());
                fInstructor.setPhoneNumber(instructor.getPhoneNumber());
                fInstructor.setName(instructor.getName());
            } else if (instructor instanceof VisitingResearcher && fInstructor instanceof VisitingResearcher) {
                ((VisitingResearcher) fInstructor).setHourlySalary(((VisitingResearcher) instructor).getHourlySalary());
                fInstructor.setPhoneNumber(instructor.getPhoneNumber());
                fInstructor.setName(instructor.getName());
            }
            entityManager.merge(fInstructor);
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
    public List<Instructor> findAll() {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        return entityManager.createQuery("from  Instructor ", Instructor.class).getResultList();
    }

    @Override
    public Instructor findById(long id) {
        entityManager = EntityManagerUtilities.getEntityManager("myJPAConfig");
        return entityManager.find(Instructor.class, id);
    }
}
