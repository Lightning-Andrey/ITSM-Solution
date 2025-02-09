package by.itsm.incident.service;

import by.itsm.exception.DataNotFoundException;
import by.itsm.incident.dto.IncidentCreateDTO;
import by.itsm.incident.dto.IncidentReadDTO;
import by.itsm.incident.dto.IncidentUpdateDTO;
import by.itsm.incident.entity.Incident;
import by.itsm.incident.repository.IncidentRepository;
import by.itsm.incident.service.mapper.IncidentMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class IncidentService {
  private static final String INCIDENT_NOT_FOUND = "Инцидент не найден";

  private IncidentMapper incidentMapper;
  private IncidentRepository incidentRepository;

  private Incident saveIncident(Incident incident) {
    return incidentRepository.save(incident);
  }

  @Transactional
  public IncidentReadDTO createIncident(IncidentCreateDTO dto) {
    return incidentMapper.toDto(saveIncident(incidentMapper.toEntity(dto)));
  }

  @Transactional
  public IncidentReadDTO updateIncident(String id, IncidentUpdateDTO dto) {
    Incident updatedIncident =
        incidentRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(INCIDENT_NOT_FOUND));
    incidentMapper.updateEntity(dto, updatedIncident);
    return incidentMapper.toDto(saveIncident(updatedIncident));
  }

  @Transactional
  public IncidentReadDTO getIncident(String id) {
    Incident existingIncident =
        incidentRepository
            .findById(id)
            .orElseThrow(() -> new DataNotFoundException(INCIDENT_NOT_FOUND));
    return incidentMapper.toDto(existingIncident);
  }

  @Transactional
  public List<IncidentReadDTO> getAllIncidents() {
    return incidentRepository.findAll().stream().map(incidentMapper::toDto).toList();
  }

  @Transactional
  public void deleteIncident(String id) {
    if (!incidentRepository.existsById(id)) throw new DataNotFoundException(INCIDENT_NOT_FOUND);
    incidentRepository.deleteById(id);
  }
}
