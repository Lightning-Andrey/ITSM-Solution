package by.itsm.incident.entity;

import by.itsm.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "journals")
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Journal extends BaseEntity {

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @JsonBackReference
  @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<IncidentInJournal> incidentInJournalList;
}
