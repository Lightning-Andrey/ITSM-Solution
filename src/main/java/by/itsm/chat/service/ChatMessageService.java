package by.itsm.chat.service;

import by.itsm.chat.dto.ChatMessageCreateUpdateDTO;
import by.itsm.chat.dto.ChatMessageReadDTO;
import by.itsm.chat.entity.ChatMessage;
import by.itsm.chat.repository.ChatMessageRepository;
import by.itsm.chat.service.mapper.ChatMessageMapper;
import by.itsm.exception.DataNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ChatMessageService {
  private static final String CHAT_MESSAGE_NOT_FOUND = "Инцидент не найден";

  private ChatMessageMapper chatMessageMapper;
  private ChatMessageRepository chatMessageRepository;

  private ChatMessage saveChatMessage(ChatMessage chatMessage) {
    return chatMessageRepository.save(chatMessage);
  }

  @Transactional
  public ChatMessageReadDTO createChatMessage(ChatMessageCreateUpdateDTO dto) {
    return chatMessageMapper.toDto(saveChatMessage(chatMessageMapper.toEntity(dto)));
  }

  @Transactional
  public ChatMessageReadDTO updateChatMessage(String id, ChatMessageCreateUpdateDTO dto) {
    ChatMessage updatedChatMessage =
        chatMessageRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(CHAT_MESSAGE_NOT_FOUND));
    chatMessageMapper.updateEntity(dto, updatedChatMessage);
    return chatMessageMapper.toDto(saveChatMessage(updatedChatMessage));
  }

  @Transactional
  public ChatMessageReadDTO getChatMessage(String id) {
    ChatMessage existingChatMessage =
        chatMessageRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(CHAT_MESSAGE_NOT_FOUND));
    return chatMessageMapper.toDto(existingChatMessage);
  }

  @Transactional
  public List<ChatMessageReadDTO> getAllChatMessages() {
    return chatMessageRepository.findAll().stream().map(chatMessageMapper::toDto).toList();
  }

  @Transactional
  public void deleteChatMessage(String id) {
    if (!chatMessageRepository.existsById(id))
      throw new DataNotFoundException(CHAT_MESSAGE_NOT_FOUND);
    chatMessageRepository.deleteById(id);
  }
}
