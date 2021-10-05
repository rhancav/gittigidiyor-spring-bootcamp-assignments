package dev.schoolmanagement.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLog extends AbstractLog {
    private String type;
    private String message;
}
