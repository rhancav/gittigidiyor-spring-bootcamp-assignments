package dev.schoolmanagement.DTO.response;

import lombok.Getter;

/**
 * Main success response skeleton
 */
@Getter
public class SuccessResponse extends BaseResponse {
    public SuccessResponse(String message) {
        super(message, true);
    }
}
