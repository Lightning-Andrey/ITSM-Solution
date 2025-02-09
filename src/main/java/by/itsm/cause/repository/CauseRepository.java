package by.itsm.cause.repository;

import by.itsm.cause.entity.Cause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CauseRepository
    extends JpaRepository<Cause, String>, JpaSpecificationExecutor<Cause> {
  boolean existsByName(String name);
}
