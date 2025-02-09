package by.itsm.incident.dto;

import by.itsm.incident.entity.IncidentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncidentCreateDTO {
  @NotBlank private String title;
  @NotBlank private String description;
  @NotNull private IncidentType type;
}
