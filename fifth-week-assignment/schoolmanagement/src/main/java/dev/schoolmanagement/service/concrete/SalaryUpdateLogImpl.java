package dev.schoolmanagement.service.concrete;

import dev.schoolmanagement.entity.SalaryUpdateLog;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.exceptions.NonNullableException;
import dev.schoolmanagement.repository.SalaryUpdateLogRepository;
import dev.schoolmanagement.service.SalaryUpdateLogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@NoArgsConstructor
public class SalaryUpdateLogImpl implements SalaryUpdateLogService {
    @Autowired
    SalaryUpdateLogRepository salaryUpdateLogRepository;
    @Override
    public SalaryUpdateLog save(SalaryUpdateLog salaryUpdateLog) {
        return salaryUpdateLogRepository.save(salaryUpdateLog);
    }

    @Override
    public List<SalaryUpdateLog> findAll() {
        return salaryUpdateLogRepository.findAll();
    }

    @Override
    public SalaryUpdateLog findById(long id) {
        return salaryUpdateLogRepository.findById(id).get();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public SalaryUpdateLog findByInstructorAndDate(Long instructorId, LocalDate createdDate) {
        if (instructorId == null || createdDate == null){
            throw new NonNullableException("Instructor ID and creation date cannot be null.");
        }
        return salaryUpdateLogRepository.findByInstructorIdAndCreatedDate(instructorId, createdDate).orElseThrow(()-> new EntityNotFoundException("Salary update log not found with given fields."));
    }
}
