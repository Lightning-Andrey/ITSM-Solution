package by.itsm.user.service;

import by.itsm.exception.DataAlreadyExistsException;
import by.itsm.user.entity.User;
import by.itsm.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository repository;

  public User save(User user) {
    return repository.save(user);
  }

  public User createUser(User user) {
    if (repository.existsByUsername(user.getUsername())) {
      throw new DataAlreadyExistsException("Пользователь с таким именем уже существует");
    }
    return save(user);
  }
}
