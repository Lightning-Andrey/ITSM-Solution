package by.itsm.chat.dto;

import lombok.Data;

@Data
public class ChatMessageCreateUpdateDTO {
  private String chatId;
  private String senderId;
  private String content;
}
