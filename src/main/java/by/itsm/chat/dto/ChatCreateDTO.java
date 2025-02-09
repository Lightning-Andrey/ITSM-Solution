package by.itsm.chat.dto;

import lombok.Data;

@Data
public class ChatCreateDTO {
  private String incidentId;
  private String reporterId;
  private String respondentId;
  private String title;
}
