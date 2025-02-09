package by.itsm.incident.service.mapper;

import by.itsm.incident.dto.JournalCreateDTO;
import by.itsm.incident.dto.JournalReadDTO;
import by.itsm.incident.dto.JournalUpdateDTO;
import by.itsm.incident.entity.Journal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface JournalMapper {
  Journal toEntity(JournalCreateDTO dto);

  JournalReadDTO toDto(Journal journal);

  void updateEntity(JournalUpdateDTO dto, @MappingTarget Journal journal);
}
