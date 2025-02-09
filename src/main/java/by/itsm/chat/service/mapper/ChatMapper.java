package by.itsm.chat.service.mapper;

import by.itsm.chat.dto.ChatCreateDTO;
import by.itsm.chat.dto.ChatReadDTO;
import by.itsm.chat.dto.ChatUpdateDTO;
import by.itsm.chat.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMapper {
  Chat toEntity(ChatCreateDTO dto);

  ChatReadDTO toDto(Chat chat);

  void updateEntity(ChatUpdateDTO dto, @MappingTarget Chat chat);
}
