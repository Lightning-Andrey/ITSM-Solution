package by.itsm.chat.service;

import by.itsm.chat.dto.ChatCreateDTO;
import by.itsm.chat.dto.ChatReadDTO;
import by.itsm.chat.dto.ChatUpdateDTO;
import by.itsm.chat.entity.Chat;
import by.itsm.chat.repository.ChatRepository;
import by.itsm.chat.service.mapper.ChatMapper;
import by.itsm.exception.DataNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ChatService {
  private static final String CHAT_NOT_FOUND = "Инцидент не найден";

  private ChatMapper chatMapper;
  private ChatRepository chatRepository;

  private Chat saveChat(Chat chat) {
    return chatRepository.save(chat);
  }

  @Transactional
  public ChatReadDTO createChat(ChatCreateDTO dto) {
    return chatMapper.toDto(saveChat(chatMapper.toEntity(dto)));
  }

  @Transactional
  public ChatReadDTO updateChat(String id, ChatUpdateDTO dto) {
    Chat updatedChat =
        chatRepository.findById(id).orElseThrow(() -> new DataNotFoundException(CHAT_NOT_FOUND));
    chatMapper.updateEntity(dto, updatedChat);
    return chatMapper.toDto(saveChat(updatedChat));
  }

  @Transactional
  public ChatReadDTO getChat(String id) {
    Chat existingChat =
        chatRepository.findById(id).orElseThrow(() -> new DataNotFoundException(CHAT_NOT_FOUND));
    return chatMapper.toDto(existingChat);
  }

  @Transactional
  public List<ChatReadDTO> getAllChats() {
    return chatRepository.findAll().stream().map(chatMapper::toDto).toList();
  }

  @Transactional
  public void deleteChat(String id) {
    if (!chatRepository.existsById(id)) throw new DataNotFoundException(CHAT_NOT_FOUND);
    chatRepository.deleteById(id);
  }
}
