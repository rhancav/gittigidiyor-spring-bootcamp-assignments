package dev.schoolmanagement.repository;

import dev.schoolmanagement.entity.SalaryUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SalaryUpdateLogRepository extends JpaRepository<SalaryUpdateLog, Long> {
    Optional<SalaryUpdateLog> findByInstructorIdAndCreatedDate(Long instructorId, LocalDate date);
}