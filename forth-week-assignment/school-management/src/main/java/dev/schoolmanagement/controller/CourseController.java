package dev.schoolmanagement.controller;

import dev.schoolmanagement.DTO.CourseDTO;
import dev.schoolmanagement.DTO.response.CreationSuccess;
import dev.schoolmanagement.DTO.response.DeletionSuccess;
import dev.schoolmanagement.DTO.response.UpdateSuccess;
import dev.schoolmanagement.service.CourseService;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
    CourseService courseService;

    @PostMapping
    public ResponseEntity<CreationSuccess<CourseDTO>> save(@RequestBody @Valid CourseDTO course) {
        return ResponseEntity.ok(new CreationSuccess<>(courseService.save(course)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletionSuccess> deleteByID(@PathVariable @ApiParam(example = "5") long id) {
        courseService.deleteById(id);
        return ResponseEntity.ok(new DeletionSuccess(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable @ApiParam(example = "5") long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @PutMapping
    public ResponseEntity<UpdateSuccess<CourseDTO>> update(@RequestBody @Valid CourseDTO courseDTO) {
        return ResponseEntity.ok(new UpdateSuccess<>(courseService.update(courseDTO)));
    }
}
