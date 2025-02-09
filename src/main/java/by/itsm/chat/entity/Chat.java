package by.itsm.chat.entity;

import by.itsm.BaseEntity;
import by.itsm.incident.entity.Incident;
import by.itsm.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "chats")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Chat extends BaseEntity {

  @OneToOne
  @JoinColumn(name = "incident_id")
  private Incident incident;

  @ManyToOne
  @JoinColumn(name = "reporter_user_id")
  private User reporter;

  @ManyToOne
  @JoinColumn(name = "respondent_user_id")
  private User respondent;

  @Column(nullable = false)
  private String title;
}
