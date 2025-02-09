package by.itsm.chat.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MessageAttachmentReadDTO {
  private String id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String chatMessageId;
  private String filename;
  private String contentType;
  private Long size;
  private String fileLink;
}
