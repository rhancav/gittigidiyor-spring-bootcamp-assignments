package dev.schoolmanagement.DTO.response;

import lombok.Getter;
// Deletion successfull with with querried parameter
@Getter
public class DeletionSuccess extends SuccessResponse {
    public DeletionSuccess(Long id) {
        super("Entity with id " + id + " is successfully deleted");
    }
}
