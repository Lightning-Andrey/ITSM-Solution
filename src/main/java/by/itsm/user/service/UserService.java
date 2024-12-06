package by.itsm.user.service;

import by.itsm.exception.DataAlreadyExistsException;
import by.itsm.exception.DataNotFoundException;
import by.itsm.user.dto.SignUpRequest;
import by.itsm.user.dto.UserReadDTO;
import by.itsm.user.dto.UserUpdateDTO;
import by.itsm.user.entity.Role;
import by.itsm.user.entity.User;
import by.itsm.user.repository.UserRepository;
import by.itsm.user.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private static final String NOT_FOUND = "Пользователь не найден";

  private UserRepository userRepository;
  private UserMapper userMapper;

  public User save(User user) {
    return userRepository.save(user);
  }

  public User createUser(User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new DataAlreadyExistsException("Пользователь с таким именем уже существует");
    }
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new DataAlreadyExistsException("Пользователь с таким email уже существует");
    }

    return save(user);
  }

  public User getByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new DataNotFoundException(NOT_FOUND));

  }

  public UserReadDTO updateUser(String id, UserUpdateDTO dto) {
    User updatedUser =
        userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(NOT_FOUND));
    userMapper.updateEntity(dto, updatedUser);
    return userMapper.entityToDto(save(updatedUser));
  }

  public UserReadDTO getUserById(String id) {
    var user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(NOT_FOUND));
    return userMapper.entityToDto(user);
  }

  public UserDetailsService userDetailsService() {
    return this::getByUsername;
  }

  public User getCurrentUser() {
    // Получение имени пользователя из контекста Spring Security
    var username = SecurityContextHolder.getContext().getAuthentication().getName();
    return getByUsername(username);
  }

  /**
   * @deprecated (при создании, роль пользователей не рекомендуется задавать при помощи этого метода, раздавайте через бд или функционал админа)
   */
  @Deprecated
  public void getServiceDeskEmployeeRights() {
    var user = getCurrentUser();
    user.setRole(Role.SERVICE_DESK_EMPLOYEE);
    save(user);
  }

  /**
   * @deprecated (при создании, роль пользователей не рекомендуется задавать при помощи этого метода, раздавайте через бд или функционал админа)
   */
  @Deprecated
  public void getItSupportEmployeeRights() {
    var user = getCurrentUser();
    user.setRole(Role.IT_SUPPORT_EMPLOYEE);
    save(user);
  }

  /**
   * @deprecated (при создании, роль пользователей не рекомендуется задавать при помощи этого метода, раздавайте через бд или функционал админа)
   */
  @Deprecated
  public void getIncidentSpecialistRights() {
    var user = getCurrentUser();
    user.setRole(Role.INCIDENT_SPECIALIST);
    save(user);
  }
}
