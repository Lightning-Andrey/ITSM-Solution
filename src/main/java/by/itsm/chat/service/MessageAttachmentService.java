package by.itsm.chat.service;

import by.itsm.chat.dto.MessageAttachmentReadDTO;
import by.itsm.chat.entity.MessageAttachment;
import by.itsm.chat.repository.MessageAttachmentRepository;
import by.itsm.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MessageAttachmentService {
  private static final String MESSAGE_ATTACHMENT_NOT_FOUND = "Вложение не найдено";

  private MessageAttachmentRepository messageAttachmentRepository;

  @Transactional
  public MessageAttachmentReadDTO saveMessageAttachment(MessageAttachment messageAttachment) {
    var dto = new MessageAttachmentReadDTO();
    messageAttachmentRepository.save(messageAttachment);
    return dto;
  }

  @Transactional
  public void deleteMessageAttachment(String id) {
    if (!messageAttachmentRepository.existsById(id))
      throw new DataNotFoundException(MESSAGE_ATTACHMENT_NOT_FOUND);
    messageAttachmentRepository.deleteById(id);
  }
}
