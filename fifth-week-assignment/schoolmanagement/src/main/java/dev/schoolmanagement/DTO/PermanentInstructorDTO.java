package dev.schoolmanagement.DTO;

import dev.schoolmanagement.utility.Constants;
import dev.schoolmanagement.utility.ErrorMessages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(parent = InstructorDTO.class)
public class PermanentInstructorDTO extends InstructorDTO {
    @ApiModelProperty(example = "2000.0")
    @Positive(message = "Cannot be lower then zero.")
    private double fixedSalary;
}
