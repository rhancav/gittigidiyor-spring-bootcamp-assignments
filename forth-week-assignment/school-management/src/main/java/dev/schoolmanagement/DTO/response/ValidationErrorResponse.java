package dev.schoolmanagement.DTO.response;

import dev.schoolmanagement.exceptions.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * For better presentation of validation errors which
 * are handled by {@link GlobalExceptionHandler} class.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse extends ErrorResponse{
    private Map<String, String> errors;

    public ValidationErrorResponse(int status, Map<String, String> errors) {
        super("Field validations failed.", status);
        this.errors = errors;
    }
}
