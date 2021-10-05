package dev.schoolmanagement.service.concrete;

import dev.schoolmanagement.DTO.InstructorDTO;
import dev.schoolmanagement.DTO.PermanentInstructorDTO;
import dev.schoolmanagement.DTO.VisitingResearcherDTO;
import dev.schoolmanagement.entity.Instructor;
import dev.schoolmanagement.entity.PermanentInstructor;
import dev.schoolmanagement.entity.VisitingResearcher;
import dev.schoolmanagement.exceptions.EntityNotFoundException;
import dev.schoolmanagement.exceptions.InstructorAlreadyExistsException;
import dev.schoolmanagement.exceptions.NonNullableException;
import dev.schoolmanagement.mappers.InstructorMapper;
import dev.schoolmanagement.repository.InstructorRepository;
import dev.schoolmanagement.service.InstructorService;
import dev.schoolmanagement.utility.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * InstructorService implementation class. All of its methods are declared
 * readOnly at class level, writing methods are excluded by @Transactional
 * annotation.
 *
 * @author Erhan Cavdar.
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class InstructorServiceImpl implements InstructorService {
    InstructorRepository instructorRepository;
    InstructorMapper instructorMapper;

    /**
     *{@inheritDoc}
     */
    @Override
    @Transactional
    public InstructorDTO save(InstructorDTO instructorDTO) {
        if (instructorDTO == null){
            throw new NonNullableException("Instructor cannot be null");
        }
        else if (instructorRepository.existsByPhoneNumber(instructorDTO.getPhoneNumber())) {
            throw new InstructorAlreadyExistsException(Constants.INSTRUCTOR_ALREADY_EXISTS);
        }
        if (instructorDTO instanceof VisitingResearcherDTO) {
            return instructorMapper.mapToDTO(instructorRepository.save(instructorMapper.mapToVisitingResearcher((VisitingResearcherDTO) instructorDTO)));
        }
        return instructorMapper.mapToDTO(instructorRepository.save(instructorMapper.mapToPermanentInstructor((PermanentInstructorDTO) instructorDTO)));
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public List<InstructorDTO> findAll() {
        return instructorRepository.findAll()
                .stream()
                .map(
                        (e) -> e instanceof PermanentInstructor ?
                                instructorMapper.mapToDTO((PermanentInstructor) e) :
                                instructorMapper.mapToDTO((VisitingResearcher) e))
                .collect(Collectors.toList());
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public InstructorDTO findById(long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Constants.INSTRUCTOR_NOT_FOUND));
        return instructor instanceof PermanentInstructor ?
                instructorMapper.mapToDTO((PermanentInstructor) instructor) :
                instructorMapper.mapToDTO((VisitingResearcher) instructor);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteById(long id) {
        if (!instructorRepository.existsById(id)) {
            throw new EntityNotFoundException(Constants.INSTRUCTOR_NOT_FOUND);
        }
        instructorRepository.deleteById(id);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public InstructorDTO update(InstructorDTO instructor) {
        if (instructor == null){
            throw new NonNullableException("Instructor cannot be null");
        }
        else if (!instructorRepository.existsById(instructor.getId())) {
            throw new EntityNotFoundException(Constants.INSTRUCTOR_NOT_FOUND);
        }
        if (instructor instanceof PermanentInstructorDTO) {
            return instructorMapper.mapToDTO(instructorRepository.save(instructorMapper.mapToPermanentInstructor((PermanentInstructorDTO) instructor)));
        }
        return instructorMapper.mapToDTO(instructorRepository.save(instructorMapper.mapToVisitingResearcher((VisitingResearcherDTO) instructor)));
    }
}