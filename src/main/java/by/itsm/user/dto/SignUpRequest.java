package by.itsm.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {
  @NotEmpty private String username;
  @Email @NotNull private String email;
  @NotEmpty private String password;
  @NotEmpty private String firstName;
  private String middleName;
  @NotEmpty private String lastName;
}
