package by.itsm.cause.service.mapper;

import by.itsm.cause.dto.CauseCreateDTO;
import by.itsm.cause.dto.CauseReadDTO;
import by.itsm.cause.dto.CauseUpdateDTO;
import by.itsm.cause.entity.Cause;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface CauseMapper {
  Cause toEntity(CauseCreateDTO dto);

  CauseReadDTO toDto(Cause cause);

  void updateEntity(CauseUpdateDTO dto, @MappingTarget Cause cause);
}
