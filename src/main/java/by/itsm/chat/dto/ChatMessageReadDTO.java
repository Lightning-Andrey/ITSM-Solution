package by.itsm.chat.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ChatMessageReadDTO {
  private String id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private ChatReadDTO chat;
  private String senderId;
  private String content;
  private Boolean isRead;
}
