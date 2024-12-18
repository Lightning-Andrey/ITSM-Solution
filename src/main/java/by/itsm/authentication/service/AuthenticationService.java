package by.itsm.authentication.service;

import by.itsm.authentication.dto.JwtAuthenticationResponse;
import by.itsm.authentication.dto.SignInRequest;
import by.itsm.authentication.dto.SignUpRequest;
import by.itsm.user.entity.Role;
import by.itsm.user.service.UserService;
import by.itsm.user.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
  private final UserService userService;
  private final JwtProvider jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final UserMapper userMapper;

  public JwtAuthenticationResponse signUp(SignUpRequest request) {

    var user = userMapper.toEntity(request);
    user.setRole(Role.ROLE_CUSTOMER);
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    user = userService.createUser(user);

    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponse(jwt);
  }

  public JwtAuthenticationResponse signIn(SignInRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    var user = userService.userDetailsService().loadUserByUsername(request.getUsername());

    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponse(jwt);
  }
}
