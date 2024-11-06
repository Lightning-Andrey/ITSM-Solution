package by.itsm.handler;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@Builder
@ToString
public class ErrorResponse {

  @CreatedDate private LocalDateTime timestamp;
  private ErrorCode errorCode;
  private String message;
}
