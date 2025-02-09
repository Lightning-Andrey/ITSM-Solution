package by.itsm.solution.service;

import by.itsm.exception.DataNotFoundException;
import by.itsm.solution.dto.SolutionCreateDTO;
import by.itsm.solution.dto.SolutionReadDTO;
import by.itsm.solution.dto.SolutionUpdateDTO;
import by.itsm.solution.entity.Solution;
import by.itsm.solution.repository.SolutionRepository;
import by.itsm.solution.service.mapper.SolutionMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SolutionService {
  private static final String SOLUTION_NOT_FOUND = "Инцидент не найден";

  private SolutionMapper solutionMapper;
  private SolutionRepository solutionRepository;

  private Solution saveSolution(Solution solution) {
    return solutionRepository.save(solution);
  }

  @Transactional
  public SolutionReadDTO createSolution(SolutionCreateDTO dto) {
    return solutionMapper.toDto(saveSolution(solutionMapper.toEntity(dto)));
  }

  @Transactional
  public SolutionReadDTO updateSolution(String id, SolutionUpdateDTO dto) {
    Solution updatedSolution =
        solutionRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(SOLUTION_NOT_FOUND));
    solutionMapper.updateEntity(dto, updatedSolution);
    return solutionMapper.toDto(saveSolution(updatedSolution));
  }

  @Transactional
  public SolutionReadDTO getSolution(String id) {
    Solution existingSolution =
        solutionRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(SOLUTION_NOT_FOUND));
    return solutionMapper.toDto(existingSolution);
  }

  @Transactional
  public List<SolutionReadDTO> getAllSolutions() {
    return solutionRepository.findAll().stream().map(solutionMapper::toDto).toList();
  }

  @Transactional
  public void deleteSolution(String id) {
    if (!solutionRepository.existsById(id)) throw new DataNotFoundException(SOLUTION_NOT_FOUND);
    solutionRepository.deleteById(id);
  }
}
