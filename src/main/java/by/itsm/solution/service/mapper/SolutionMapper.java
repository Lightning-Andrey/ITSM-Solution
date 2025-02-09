package by.itsm.solution.service.mapper;

import by.itsm.solution.dto.SolutionCreateDTO;
import by.itsm.solution.dto.SolutionReadDTO;
import by.itsm.solution.dto.SolutionUpdateDTO;
import by.itsm.solution.entity.Solution;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolutionMapper {
  Solution toEntity(SolutionCreateDTO dto);

  SolutionReadDTO toDto(Solution solution);

  void updateEntity(SolutionUpdateDTO dto, @MappingTarget Solution solution);
}
