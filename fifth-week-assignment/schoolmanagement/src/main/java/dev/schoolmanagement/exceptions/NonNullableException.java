package dev.schoolmanagement.exceptions;

/**
 * Can be used for non-nullable fields or service layer null checks.
 * It is thrown when the checked fields is passed with a "null" field
 * at run time.
 */
public class NonNullableException extends RuntimeException{
    public NonNullableException(String message) {
        super(message);
    }
}
