package by.itsm.cause.entity;

import by.itsm.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "causes")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cause extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, columnDefinition = "text")
  private String description;
}
