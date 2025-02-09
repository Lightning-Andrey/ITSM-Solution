package by.itsm.incident.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class JournalReadDTO {
  private String id;
  private String title;
  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
