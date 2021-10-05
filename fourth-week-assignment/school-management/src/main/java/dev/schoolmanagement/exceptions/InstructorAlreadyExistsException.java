package dev.schoolmanagement.exceptions;

public class InstructorAlreadyExistsException extends EntityAlreadyExistsException {
    public InstructorAlreadyExistsException(String message) {
        super(message);
    }
}