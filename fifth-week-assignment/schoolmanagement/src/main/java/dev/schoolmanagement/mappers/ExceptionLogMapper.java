package dev.schoolmanagement.mappers;

import dev.schoolmanagement.DTO.ExceptionLogDTO;
import dev.schoolmanagement.entity.ExceptionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExceptionLogMapper {
    ExceptionLog mapToPersistable(ExceptionLogDTO source);
    ExceptionLogDTO mapToDTO(ExceptionLog source);
}
