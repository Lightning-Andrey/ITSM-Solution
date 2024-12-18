package by.itsm.cause.repository;

import by.itsm.cause.entity.Cause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CauseRepository extends JpaRepository<Cause, String> {
  boolean existsByName(String name);
}
