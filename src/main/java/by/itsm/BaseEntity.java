package by.itsm;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@MappedSuperclass
public abstract class BaseEntity {
  @Id private String id = RandomStringUtils.random(16);
}
