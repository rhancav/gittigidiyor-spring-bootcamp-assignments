package dev.schoolmanagement.controller;

import dev.schoolmanagement.DTO.ExceptionLogDTO;
import dev.schoolmanagement.service.ExceptionLogService;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/exception-logs")
@AllArgsConstructor
public class ExceptionLogController {
    ExceptionLogService exceptionLogService;

    @GetMapping
    public ResponseEntity<List<ExceptionLogDTO>> findAll(){
        return ResponseEntity.ok(exceptionLogService.findAll());
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<ExceptionLogDTO>> findAllByType(@PathVariable @ApiParam(example = "RunTimeException", type = "String") String type){
        return ResponseEntity.ok(exceptionLogService.findAllByType(type));
    }
    @GetMapping("/search/creation-date/{creationDate}")
    public ResponseEntity<List<ExceptionLogDTO>> findAllByCreationDate(@PathVariable @ApiParam(example = "2021-09-01T23:40:02.237301Z") Instant creationDate){
        return ResponseEntity.ok(exceptionLogService.findAllByCreationDate(creationDate));
    }
}
