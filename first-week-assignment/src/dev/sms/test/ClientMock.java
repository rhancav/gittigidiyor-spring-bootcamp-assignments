package dev.sms.test;

import dev.sms.controller.CourseController;
import dev.sms.controller.InstructorController;
import dev.sms.controller.StudentController;
import dev.sms.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ClientMock {
    public static void main(String[] args) {
        // persistTestData();
        if (dataUpdatable()&&dataDeletable()){
            System.out.println("CRUD operations seem to function well.");
        }
        else{
            System.out.println("Works on my computer???");
        }
    }

    // Persist some dummy
    public static void persistTestData() {
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        InstructorController instructorController = new InstructorController();

        // Dummy Instructors
        Instructor instructor = new PermanentInstructor("Osman", "5231551212", 1250.00F);
        Instructor instructor2 = new PermanentInstructor("Ali", "5231551212", 1250.00F);
        Instructor instructor3 = new VisitingResearcher("Ahmet", "5231551212", 1250.00F);


        // Dummy courses
        Course course = new Course("Math", "M627", 10.0F, instructor);
        Course course2 = new Course("Chemistry", "C627", 10.0F, instructor2);
        Course course3 = new Course("Physics", "P627", 10.0F, instructor3);

        // List of courses for Student courses field
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        courses.add(course2);
        courses.add(course3);

        // Dummy students
        Student student = new Student("Erhan", "Istanbul", LocalDate.of(1993, 7, 26), Gender.MALE);
        Student student2 = new Student("Bahri", "Izmir", LocalDate.of(1993, 7, 26), Gender.FEMALE);
        Student student3 = new Student("Ayşe", "Ankara", LocalDate.of(1993, 7, 26), Gender.MALE);

        // Persist instructors
        instructorController.save(instructor);
        instructorController.save(instructor2);
        instructorController.save(instructor3);

        // Persist courses
        courses.forEach(courseController::save);


        // Persist students
        studentController.save(student);
        studentController.save(student2);
        studentController.save(student3);

        System.out.println("Persisted all the dummies successfully..");

    }

    // Test update operations
    public static boolean dataUpdatable() {
        // Controller objects
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        InstructorController instructorController = new InstructorController();

        boolean dataUpdatable;
        if (tableEmpty()) {
            persistTestData();
        }

        try {
            Student student = new Student("Ahmet","Bursa",LocalDate.of(12,6,22),Gender.MALE);
            instructorController.update(instructorController.findAll().get(0),1);
            courseController.update(courseController.findById(1), 1);
            studentController.update(student,1);
            dataUpdatable = true;
        }
        catch (Exception e){
            e.printStackTrace();
            dataUpdatable = false;
        }

        return dataUpdatable;
    }

    // Test delete operations
    public static boolean dataDeletable() {
        //Controller objects
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        InstructorController instructorController = new InstructorController();

        boolean dataDeletable;

        if (tableEmpty()) {
            persistTestData();
        }
        try {
            //Try to delete
            instructorController.delete(instructorController.findAll().get(0));
            courseController.delete(courseController.findAll().get(0));
            studentController.delete(studentController.findAll().get(0));
            dataDeletable = true;
        } catch (Exception e) {
            e.printStackTrace();
            dataDeletable = false;
        }
        return dataDeletable;

    }

    // Returns true if any of the tables doesnt contain any data
    public static boolean tableEmpty() {
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        InstructorController instructorController = new InstructorController();
        return studentController.findAll().size() <= 0 || courseController.findAll().size() <= 0 || instructorController.findAll().size() <= 0;
    }
}
