package dev.schoolmanagement.DTO.response;

import lombok.Getter;

/** Generic creation success response which returns the persisted
 * data.
 * @param <T> Persisted type
 */
@Getter
public class CreationSuccess<T> extends SuccessResponse {
    private final T savedData;

    public CreationSuccess(T savedData) {
        super(savedData.getClass().getSimpleName() + " is created successfully");
        this.savedData = savedData;
    }
}
