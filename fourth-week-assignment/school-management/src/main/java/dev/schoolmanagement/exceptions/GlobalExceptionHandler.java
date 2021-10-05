package dev.schoolmanagement.exceptions;

import dev.schoolmanagement.DTO.ExceptionLogDTO;
import dev.schoolmanagement.DTO.response.ErrorResponse;
import dev.schoolmanagement.DTO.response.ValidationErrorResponse;
import dev.schoolmanagement.entity.ExceptionLog;
import dev.schoolmanagement.service.ExceptionLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Base global exception handler class which also
 * logs thrown exceptions via persistErrorLog method.
 */
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    ExceptionLogService exceptionLogService;

    // Handling validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ValidationErrorResponse> handleException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        // Get BindingResult object
        BindingResult bindingResult = exception.getBindingResult();
        // Populate errors map with field name and error message
        bindingResult.getFieldErrors().forEach((e) -> errors.put(e.getField(),e.getDefaultMessage()));
        return new ResponseEntity<ValidationErrorResponse>(new ValidationErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(),errors), HttpStatus.NOT_ACCEPTABLE);
    }
    // Handling null fields & parameters
    @ExceptionHandler(NonNullableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ValidationErrorResponse> handleException(NonNullableException exception){
        this.persistErrorLog(exception);
        return new ResponseEntity<ValidationErrorResponse>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    // Handle entity not found
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException exception) {
        this.persistErrorLog(exception);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
    // Handle course already exist
    @ExceptionHandler(CourseAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleException(CourseAlreadyExistsException exception) {
        this.persistErrorLog(exception);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()), HttpStatus.NOT_ACCEPTABLE);
    }
    // Handle instructor already exist
    @ExceptionHandler(InstructorAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleException(InstructorAlreadyExistsException exception) {
        this.persistErrorLog(exception);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()), HttpStatus.NOT_ACCEPTABLE);
    }
    // Handle student already exist
    @ExceptionHandler(StudentAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleException(StudentAlreadyExistsException exception) {
        this.persistErrorLog(exception);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()), HttpStatus.NOT_ACCEPTABLE);
    }
    // Handle age validation exception
    @ExceptionHandler(StudentAgeNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleException(StudentAgeNotValidException exception) {
        this.persistErrorLog(exception);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()), HttpStatus.NOT_ACCEPTABLE);
    }

    // Handle max number of students exception
    @ExceptionHandler(StudentNumberForOneCourseExceededException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleException(StudentNumberForOneCourseExceededException exception) {
        this.persistErrorLog(exception);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()), HttpStatus.NOT_ACCEPTABLE);
    }


    /**
     * Utility method to create a {@link ExceptionLog} object and persists it
     * via {@link ExceptionLogService} to database for logging
     * purposes.
     *
     * @param runtimeException Exception object to persist.
     */
    private void persistErrorLog(RuntimeException runtimeException) {
        exceptionLogService.save(new ExceptionLogDTO(runtimeException.getClass().getSimpleName(), runtimeException.getMessage()));
    }

}
