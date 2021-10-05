package dev.schoolmanagement.DTO.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@ApiModel(description = "Request DTO for updating given Instructors salary.")
public class InstructorSalaryUpdate {
    @ApiModelProperty(required = true, notes = "Can only take two inputs: RAISE or CUT.", example = "RAISE")
    @NotBlank
    private UpdateType updateType;
    @ApiModelProperty(required = true, example = "20", dataType = "Double")
    @Positive(message = "Rate cannot be lower then zero.")
    private double rate;
    public enum UpdateType{
        RAISE,
        CUT
    }
}
