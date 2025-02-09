package by.itsm.cause.service;

import by.itsm.cause.dto.CauseCreateDTO;
import by.itsm.cause.dto.CauseReadDTO;
import by.itsm.cause.dto.CauseUpdateDTO;
import by.itsm.cause.entity.Cause;
import by.itsm.cause.repository.CauseRepository;
import by.itsm.cause.service.mapper.CauseMapper;
import by.itsm.exception.DataAlreadyExistsException;
import by.itsm.exception.DataNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CauseService {
  private static final String CAUSE_NOT_FOUND = "Причина не найдена";

  private CauseMapper causeMapper;
  private CauseRepository causeRepository;

  private Cause saveCause(Cause cause) {
    return causeRepository.save(cause);
  }

  @Transactional
  public CauseReadDTO createCause(CauseCreateDTO dto) {
    if (causeRepository.existsByName(dto.getName()))
      throw new DataAlreadyExistsException("Причина с таким именем уже существует");
    return causeMapper.toDto(saveCause(causeMapper.toEntity(dto)));
  }

  @Transactional
  public CauseReadDTO updateCause(String id, CauseUpdateDTO dto) {
    Cause updatedCause =
        causeRepository.findById(id).orElseThrow(() -> new DataNotFoundException(CAUSE_NOT_FOUND));
    causeMapper.updateEntity(dto, updatedCause);
    return causeMapper.toDto(saveCause(updatedCause));
  }

  @Transactional
  public CauseReadDTO getCause(String id) {
    Cause existingCause =
        causeRepository.findById(id).orElseThrow(() -> new DataNotFoundException(CAUSE_NOT_FOUND));
    return causeMapper.toDto(existingCause);
  }

  @Transactional
  public List<CauseReadDTO> getAllCauses() {
    return causeRepository.findAll().stream().map(causeMapper::toDto).toList();
  }

  @Transactional
  public void deleteCause(String id) {
    if (!causeRepository.existsById(id)) throw new DataNotFoundException(CAUSE_NOT_FOUND);
    causeRepository.deleteById(id);
  }
}
