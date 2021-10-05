package dev.schoolmanagement.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.schoolmanagement.entity.Course;
import dev.schoolmanagement.entity.Gender;
import dev.schoolmanagement.utility.Constants;
import dev.schoolmanagement.utility.ErrorMessages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Student Data Transfer Object")
public class StudentDTO {
    @ApiModelProperty(dataType = "Long", example = "5", notes = "Creates a new entity if not mentioned.")
    private long id;
    @ApiModelProperty(example = "Osman", dataType = "String")
    @Pattern(regexp = Constants.CHAR_ONLY_REGEX, message = ErrorMessages.CHAR_ONLY_MESSAGE)
    @NotBlank(message = ErrorMessages.NAME_FORMAT_MESSAGE)
    private String name;
    @ApiModelProperty(example = "Sarıyer", dataType = "String")
    @NotBlank(message = ErrorMessages.NAME_FORMAT_MESSAGE)
    @Pattern(regexp = Constants.CHAR_ONLY_REGEX, message = ErrorMessages.CHAR_ONLY_MESSAGE)
    private String address;
    @ApiModelProperty(dataType = "LocalDate", example = "2001-12-11", allowableValues = "yyyy-mm-dd")
    @NonNull
    private LocalDate birthday;
    @ApiModelProperty(dataType = "Gender", example = "FEMALE")
    @NonNull
    private Gender gender;
    @JsonIgnore
    private Set<Course> courses;
}
