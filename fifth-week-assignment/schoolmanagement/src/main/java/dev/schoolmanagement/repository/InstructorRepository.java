package dev.schoolmanagement.repository;

import dev.schoolmanagement.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}