package dev.schoolmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * Base abstract Log entity. Could be extended in the future for other logging purposes.
 * @author Erhan Cavdar.
 */
@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
public class BaseLog extends AbstractEntity {
}
