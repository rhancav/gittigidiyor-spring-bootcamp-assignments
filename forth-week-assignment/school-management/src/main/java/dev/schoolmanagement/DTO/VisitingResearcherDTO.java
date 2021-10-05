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
public class VisitingResearcherDTO extends InstructorDTO {
    @ApiModelProperty(example = "120.0")
    @Positive
    private double hourlySalary;
}
