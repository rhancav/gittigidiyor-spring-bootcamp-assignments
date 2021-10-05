package dev.schoolmanagement.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(parent = InstructorDTO.class)
public class PermanentInstructorDTO extends InstructorDTO {
    @ApiModelProperty(example = "2000.0")
    @Positive
    private double fixedSalary;
}
