package by.itsm.cause.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CauseCreateDTO {
  @NotEmpty private String name;
  @NotNull private String description;
}
