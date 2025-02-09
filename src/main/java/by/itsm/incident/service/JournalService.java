package by.itsm.incident.service;

import by.itsm.exception.DataNotFoundException;
import by.itsm.incident.dto.JournalCreateDTO;
import by.itsm.incident.dto.JournalReadDTO;
import by.itsm.incident.dto.JournalUpdateDTO;
import by.itsm.incident.entity.Journal;
import by.itsm.incident.repository.JournalRepository;
import by.itsm.incident.service.mapper.JournalMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class JournalService {
  private static final String JOURNAL_NOT_FOUND = "Инцидент не найден";

  private JournalMapper journalMapper;
  private JournalRepository journalRepository;

  private Journal saveJournal(Journal journal) {
    return journalRepository.save(journal);
  }

  @Transactional
  public JournalReadDTO createJournal(JournalCreateDTO dto) {
    return journalMapper.toDto(saveJournal(journalMapper.toEntity(dto)));
  }

  @Transactional
  public JournalReadDTO updateJournal(String id, JournalUpdateDTO dto) {
    Journal updatedJournal =
        journalRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(JOURNAL_NOT_FOUND));
    journalMapper.updateEntity(dto, updatedJournal);
    return journalMapper.toDto(saveJournal(updatedJournal));
  }

  @Transactional
  public JournalReadDTO getJournal(String id) {
    Journal existingJournal =
        journalRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(JOURNAL_NOT_FOUND));
    return journalMapper.toDto(existingJournal);
  }

  @Transactional
  public List<JournalReadDTO> getAllJournals() {
    return journalRepository.findAll().stream().map(journalMapper::toDto).toList();
  }

  @Transactional
  public void deleteJournal(String id) {
    if (!journalRepository.existsById(id)) throw new DataNotFoundException(JOURNAL_NOT_FOUND);
    journalRepository.deleteById(id);
  }
}
