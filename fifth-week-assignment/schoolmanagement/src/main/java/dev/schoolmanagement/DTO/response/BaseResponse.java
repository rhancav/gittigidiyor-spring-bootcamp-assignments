package dev.schoolmanagement.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Base response class for better presentation of responses.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String message;
    private boolean success;
}
