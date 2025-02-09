package by.itsm.incident.service.mapper;

import by.itsm.incident.dto.IncidentCreateDTO;
import by.itsm.incident.dto.IncidentReadDTO;
import by.itsm.incident.dto.IncidentUpdateDTO;
import by.itsm.incident.entity.Incident;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface IncidentMapper {
  Incident toEntity(IncidentCreateDTO dto);

  IncidentReadDTO toDto(Incident incident);

  void updateEntity(IncidentUpdateDTO dto, @MappingTarget Incident incident);
}
