package by.itsm.chat.entity;

import by.itsm.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "chat_message_attachments")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MessageAttachment extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "chat_message_id")
  private ChatMessage chatMessage;

  @Column(columnDefinition = "text")
  private String filename;

  @Column private String contentType;

  @Column private Long size;

  @Lob @Column private byte[] data;
}
