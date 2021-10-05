package dev.schoolmanagement.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel(description = "ExceptionLog Data Transfer Object")
public class ExceptionLogDTO {
    @NonNull
    @ApiModelProperty(example = "EntityNotFoundException")
    @NotBlank
    private String type;
    @NonNull
    @ApiModelProperty(example = "Entity not found.")
    @NotBlank
    private String message;
    @ApiModelProperty(example = "2021-09-01T23:40:02.237301Z", dataType = "Instant")
    private Instant createdDate;
}
