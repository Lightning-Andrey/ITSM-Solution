package by.itsm.incident;

import by.itsm.exception.DataNotFoundException;
import by.itsm.incident.dto.IncidentCreateDTO;
import by.itsm.incident.dto.IncidentReadDTO;
import by.itsm.incident.dto.IncidentUpdateDTO;
import by.itsm.incident.entity.Incident;
import by.itsm.incident.repository.IncidentRepository;
import by.itsm.incident.service.mapper.IncidentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncidentServiceTests {

    @Mock
    private IncidentRepository incidentRepository;

    @Mock
    private IncidentMapper incidentMapper;

    @InjectMocks
    private IncidentService incidentService;

    private Incident incident;
    private IncidentReadDTO incidentReadDTO;
    private IncidentCreateDTO incidentCreateDTO;
    private IncidentUpdateDTO incidentUpdateDTO;

    @BeforeEach
    void setUp() {
        incident = new Incident();
        incident.setId("1");

        incidentReadDTO = new IncidentReadDTO();
        incidentCreateDTO = new IncidentCreateDTO();
        incidentUpdateDTO = new IncidentUpdateDTO();
    }

    @Test
    void createIncident_ShouldReturnIncidentReadDTO() {
        when(incidentMapper.toEntity(incidentCreateDTO)).thenReturn(incident);
        when(incidentRepository.save(incident)).thenReturn(incident);
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        IncidentReadDTO result = incidentService.createIncident(incidentCreateDTO);

        assertNotNull(result);
        verify(incidentRepository).save(incident);
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void updateIncident_ShouldReturnUpdatedIncidentReadDTO() {
        when(incidentRepository.findById("1")).thenReturn(Optional.of(incident));
        doNothing().when(incidentMapper).updateEntity(incidentUpdateDTO, incident);
        when(incidentRepository.save(incident)).thenReturn(incident);
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        IncidentReadDTO result = incidentService.updateIncident("1", incidentUpdateDTO);

        assertNotNull(result);
        verify(incidentRepository).findById("1");
        verify(incidentMapper).updateEntity(incidentUpdateDTO, incident);
        verify(incidentRepository).save(incident);
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void updateIncident_ShouldThrowException_WhenIncidentNotFound() {
        when(incidentRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> incidentService.updateIncident("1", incidentUpdateDTO));

        verify(incidentRepository).findById("1");
        verifyNoMoreInteractions(incidentRepository, incidentMapper);
    }

    @Test
    void getIncident_ShouldReturnIncidentReadDTO() {
        when(incidentRepository.findById("1")).thenReturn(Optional.of(incident));
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        IncidentReadDTO result = incidentService.getIncident("1");

        assertNotNull(result);
        verify(incidentRepository).findById("1");
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void getIncident_ShouldThrowException_WhenIncidentNotFound() {
        when(incidentRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> incidentService.getIncident("1"));

        verify(incidentRepository).findById("1");
        verifyNoMoreInteractions(incidentRepository, incidentMapper);
    }

    @Test
    void getAllIncidents_ShouldReturnListOfIncidentReadDTOs() {
        List<Incident> incidents = List.of(incident);
        List<IncidentReadDTO> dtos = List.of(incidentReadDTO);

        when(incidentRepository.findAll()).thenReturn(incidents);
        when(incidentMapper.toDto(incident)).thenReturn(incidentReadDTO);

        List<IncidentReadDTO> result = incidentService.getAllIncidents();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(incidentRepository).findAll();
        verify(incidentMapper).toDto(incident);
    }

    @Test
    void deleteIncident_ShouldDeleteIncident_WhenExists() {
        when(incidentRepository.existsById("1")).thenReturn(true);
        doNothing().when(incidentRepository).deleteById("1");

        assertDoesNotThrow(() -> incidentService.deleteIncident("1"));

        verify(incidentRepository).existsById("1");
        verify(incidentRepository).deleteById("1");
    }

    @Test
    void deleteIncident_ShouldThrowException_WhenIncidentNotFound() {
        when(incidentRepository.existsById("1")).thenReturn(false);

        assertThrows(DataNotFoundException.class, () -> incidentService.deleteIncident("1"));

        verify(incidentRepository).existsById("1");
        verifyNoMoreInteractions(incidentRepository);
    }
}
