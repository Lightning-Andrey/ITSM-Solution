package by.itsm.solution.repository;

import by.itsm.solution.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, String> {}
