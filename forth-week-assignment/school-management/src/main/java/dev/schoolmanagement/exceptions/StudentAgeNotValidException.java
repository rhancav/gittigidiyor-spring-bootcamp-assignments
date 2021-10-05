package dev.schoolmanagement.exceptions;

public class StudentAgeNotValidException extends RuntimeException {
    public StudentAgeNotValidException(String message) {
        super(message);
    }
}