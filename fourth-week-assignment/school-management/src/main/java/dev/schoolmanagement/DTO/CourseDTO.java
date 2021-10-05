package dev.schoolmanagement.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.schoolmanagement.entity.Student;
import dev.schoolmanagement.utility.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@AllArgsConstructor
@ApiModel(description = "Course Data Transfer Object")
public class CourseDTO {
    @ApiModelProperty(dataType = "Long", example = "5", notes = "Creates a new entity if not mentioned.")
    private long id;
    @ApiModelProperty(required = true, dataType = "String", example = "Math")
    @NotBlank(message = Constants.NAME_FORMAT_MESSAGE)
    @Size(min = 2, max = 10)
    private String name;
    @ApiModelProperty(required = true, dataType = "String", example = "M101")
    @NotBlank(message = Constants.NAME_FORMAT_MESSAGE)
    @Size(min = 2, max = 10)
    private String courseCode;
    @ApiModelProperty(required = true, dataType = "Float", example = "4.0")
    @Min(value = 1, message = "Cannot be lower then 1")
    private float creditScore;
    @JsonIgnore
    private Set<Student> students;
    public void addStudents(Student student) {
        this.students.add(student);
    }
}
