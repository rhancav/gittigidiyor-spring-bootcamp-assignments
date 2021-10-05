package dev.schoolmanagement.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLog extends BaseLog {
    private String type;
    private String message;
}
