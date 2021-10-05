package dev.schoolmanagement.repository;

import dev.schoolmanagement.entity.ExceptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Long> {
    List<ExceptionLog> findAllByType(String type);

    List<ExceptionLog> findAllByCreatedDate(Instant creationDate);
}