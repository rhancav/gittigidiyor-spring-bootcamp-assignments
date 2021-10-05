package dev.schoolmanagement.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Main error response skeleton
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse extends BaseResponse {
    private int status;

    public ErrorResponse(String message, int status) {
        super(message, false);
        this.status = status;
    }
}
