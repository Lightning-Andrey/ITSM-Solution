package by.itsm.chat.entity;

import by.itsm.BaseEntity;
import by.itsm.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "chat_messages")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ChatMessage extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "chat_id")
  private Chat chat;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User sender;

  @Column(nullable = false, columnDefinition = "text")
  private String content;

  @Column(nullable = false)
  private Boolean isRead = false;
}
