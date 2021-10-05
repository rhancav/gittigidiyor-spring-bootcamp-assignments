package dev.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.AttributeAccessor;

import javax.persistence.AttributeOverride;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.Instant;

/**
 * Base abstract Log entity. Could be extended in the future for other logging purposes.
 * @author Erhan Cavdar.
 */
@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
public class AbstractLog extends Persistable{

}
