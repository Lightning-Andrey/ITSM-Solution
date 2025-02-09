package by.itsm.incident.entity;

import by.itsm.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "incident_in_journal")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class IncidentInJournal extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "incident_id", referencedColumnName = "id")
  private Incident incident;

  @ManyToOne
  @JoinColumn(name = "journal_id", referencedColumnName = "id")
  private Journal journal;
}
