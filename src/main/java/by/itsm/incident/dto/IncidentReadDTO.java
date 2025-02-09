package by.itsm.incident.dto;

import by.itsm.cause.dto.CauseReadDTO;
import by.itsm.incident.entity.IncidentStatus;
import by.itsm.incident.entity.IncidentType;
import by.itsm.solution.dto.SolutionReadDTO;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class IncidentReadDTO {
  private String id;
  private SolutionReadDTO solution;
  private CauseReadDTO cause;
  private String title;
  private String description;
  private IncidentStatus status;
  private IncidentType type;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
