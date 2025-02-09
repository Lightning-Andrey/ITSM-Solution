package by.itsm.chat.service.mapper;

import by.itsm.chat.dto.ChatMessageCreateUpdateDTO;
import by.itsm.chat.dto.ChatMessageReadDTO;
import by.itsm.chat.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMessageMapper {
  ChatMessage toEntity(ChatMessageCreateUpdateDTO dto);

  ChatMessageReadDTO toDto(ChatMessage chatMessage);

  void updateEntity(ChatMessageCreateUpdateDTO dto, @MappingTarget ChatMessage chatMessage);
}
