package by.itsm.incident.entity;

import by.itsm.BaseEntity;
import by.itsm.cause.entity.Cause;
import by.itsm.solution.entity.Solution;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "incidents")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Incident extends BaseEntity {

  @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<IncidentInJournal> incidentInJournalList;

  @ManyToOne
  @JoinColumn(name = "solution_id")
  @JsonManagedReference
  private Solution solution;

  @ManyToOne
  @JoinColumn(name = "cause_id")
  @JsonManagedReference
  private Cause cause;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @Column
  @Enumerated(EnumType.STRING)
  private IncidentStatus status;

  @Column
  @Enumerated(EnumType.STRING)
  private IncidentType type;
}
