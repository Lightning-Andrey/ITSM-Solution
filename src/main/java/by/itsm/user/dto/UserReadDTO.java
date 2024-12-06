package by.itsm.user.dto;

import by.itsm.user.entity.Role;
import lombok.Data;

@Data
public class UserReadDTO {
  private String username;
  private String email;
  private Role role;
  private String firstName;
  private String middleName;
  private String lastName;
}
