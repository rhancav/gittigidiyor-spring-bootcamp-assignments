package dev.schoolmanagement.mappers;

import dev.schoolmanagement.DTO.StudentDTO;
import dev.schoolmanagement.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student mapToPersistable(StudentDTO source);

    StudentDTO mapToDTO(Student student);
}
