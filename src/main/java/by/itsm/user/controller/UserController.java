package by.itsm.user.controller;

import by.itsm.user.dto.SignUpRequest;
import by.itsm.user.dto.UserReadDTO;
import by.itsm.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @PostMapping("/create")
  public ResponseEntity<UserReadDTO> create(@RequestBody SignUpRequest user) {
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
  }
}
