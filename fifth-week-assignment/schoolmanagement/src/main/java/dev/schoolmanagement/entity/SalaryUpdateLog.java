package dev.schoolmanagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "createdDate", column = @Column(name = "request_time"))
public class SalaryUpdateLog extends AbstractLog {
    private Long instructorId;
    private Double salaryBeforeUpdate;
    private Double salaryAfterUpdate;
    private Float rate;
    @Embedded
    private ClientInfo clientInfo;
}
