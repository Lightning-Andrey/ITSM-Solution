package by.itsm;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Data
@MappedSuperclass
public abstract class BaseEntity {
  @Id private String id = NanoIdUtils.randomNanoId();

  @CreatedDate private LocalDateTime createdAt;

  @CreatedDate private LocalDateTime updatedAt;
}
