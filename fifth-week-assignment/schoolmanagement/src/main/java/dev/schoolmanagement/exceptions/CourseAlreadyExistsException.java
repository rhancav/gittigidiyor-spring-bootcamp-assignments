package dev.schoolmanagement.exceptions;

public class CourseAlreadyExistsException extends EntityAlreadyExistsException {
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}