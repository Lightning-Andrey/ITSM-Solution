package by.itsm.solution.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SolutionReadDTO {
  private String id;
  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
