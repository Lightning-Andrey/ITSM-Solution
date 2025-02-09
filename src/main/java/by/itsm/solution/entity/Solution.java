package by.itsm.solution.entity;

import by.itsm.BaseEntity;
import by.itsm.incident.entity.Incident;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "solutions")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Solution extends BaseEntity {
  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @JsonBackReference
  @OneToMany(targetEntity = Incident.class, mappedBy = "solution")
  private List<Incident> incidents;
}
