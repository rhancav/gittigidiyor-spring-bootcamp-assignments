package dev.schoolmanagement.entity;

import lombok.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.Embeddable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SessionScope
@Embeddable
@Builder
public class ClientInfo {
    private String clientIp;
    private String clientURL;
    private String clientSessionId;
}
