package by.itsm.chat.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ChatReadDTO {
  private String id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private String incidentId;
  private String reporterId;
  private String respondentId;
  private String title;
}
