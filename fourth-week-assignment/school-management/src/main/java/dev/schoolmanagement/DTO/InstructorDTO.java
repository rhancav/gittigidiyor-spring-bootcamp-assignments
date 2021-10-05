package dev.schoolmanagement.DTO;

import dev.schoolmanagement.utility.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Instructor Data Transfer Object", subTypes = {PermanentInstructorDTO.class, VisitingResearcherDTO.class})
public class InstructorDTO {
    @ApiModelProperty(dataType = "Long", example = "5", notes = "Creates a new entity if not mentioned.")
    private long id;
    @ApiModelProperty(example = "Osman", dataType = "String")
    @NotBlank(message = Constants.NAME_FORMAT_MESSAGE)
    private String name;
    @ApiModelProperty(example = "5549078095", dataType = "String")
    @NotBlank(message = Constants.PHONE_NUMBER_MESSAGE)
    @Size(min = 10, max = 13, message = Constants.PHONE_NUMBER_MESSAGE)
    private String phoneNumber;
}
