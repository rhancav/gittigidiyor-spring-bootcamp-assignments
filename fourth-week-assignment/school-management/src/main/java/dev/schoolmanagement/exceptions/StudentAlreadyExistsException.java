package dev.schoolmanagement.exceptions;

public class StudentAlreadyExistsException extends EntityAlreadyExistsException {
    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}