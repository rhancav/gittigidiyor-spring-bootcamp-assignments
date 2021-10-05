package dev.schoolmanagement.service.concrete;

import dev.schoolmanagement.DTO.ExceptionLogDTO;
import dev.schoolmanagement.mappers.ExceptionLogMapper;
import dev.schoolmanagement.repository.ExceptionLogRepository;
import dev.schoolmanagement.service.ExceptionLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
/**
 * ExceptionLogService implementation class. All of its methods are declared
 * readOnly at class level, writing methods are excluded by @Transactional
 * annotation.
 *
 * @author Erhan Cavdar.
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ExceptionLogServiceImpl implements ExceptionLogService {
    ExceptionLogRepository exceptionLogRepository;
    ExceptionLogMapper exceptionLogMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ExceptionLogDTO> findAllByCreationDate(Instant creationDate) {
        return exceptionLogRepository.findAllByCreatedDate(creationDate)
                .stream()
                .map((e) -> exceptionLogMapper.mapToDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ExceptionLogDTO> findAllByType(String type) {
        return exceptionLogRepository.findAllByType(type)
                .stream()
                .map((e) -> exceptionLogMapper.mapToDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExceptionLogDTO save(ExceptionLogDTO type) {
        return exceptionLogMapper.mapToDTO(exceptionLogRepository.save(exceptionLogMapper.mapToPersistable(type)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ExceptionLogDTO> findAll() {
        return exceptionLogRepository.findAll()
                .stream()
                .map((e) -> exceptionLogMapper.mapToDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExceptionLogDTO findById(long id) {
        return exceptionLogMapper.mapToDTO(exceptionLogRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

}