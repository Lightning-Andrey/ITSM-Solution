package by.itsm.cause;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import by.itsm.cause.dto.CauseCreateDTO;
import by.itsm.cause.dto.CauseReadDTO;
import by.itsm.cause.dto.CauseUpdateDTO;
import by.itsm.cause.entity.Cause;
import by.itsm.cause.repository.CauseRepository;
import by.itsm.cause.service.mapper.CauseMapper;
import by.itsm.exception.DataAlreadyExistsException;
import by.itsm.exception.DataNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CauseServiceTest {

    @Mock private CauseRepository causeRepository;
    @Mock private CauseMapper causeMapper;

    @InjectMocks private CauseService causeService;

    private Cause cause;
    private CauseReadDTO causeReadDTO;
    private CauseCreateDTO causeCreateDTO;
    private CauseUpdateDTO causeUpdateDTO;

    @BeforeEach
    void setUp() {
        cause = new Cause();
        cause.setId("1");
        cause.setName("Test Cause");

        causeReadDTO = new CauseReadDTO();
        causeReadDTO.setId("1");
        causeReadDTO.setName("Test Cause");

        causeCreateDTO = new CauseCreateDTO();
        causeCreateDTO.setName("Test Cause");

        causeUpdateDTO = new CauseUpdateDTO();
        causeUpdateDTO.setName("Updated Cause");
    }

    @Test
    void createCause_ShouldReturnCauseReadDTO() {
        when(causeRepository.existsByName(anyString())).thenReturn(false);
        when(causeMapper.toEntity(any(CauseCreateDTO.class))).thenReturn(cause);
        when(causeRepository.save(any(Cause.class))).thenReturn(cause);
        when(causeMapper.toDto(any(Cause.class))).thenReturn(causeReadDTO);

        CauseReadDTO result = causeService.createCause(causeCreateDTO);

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(causeRepository).save(any(Cause.class));
    }

    @Test
    void createCause_ShouldThrowException_WhenCauseAlreadyExists() {
        when(causeRepository.existsByName(anyString())).thenReturn(true);

        assertThrows(DataAlreadyExistsException.class, () -> causeService.createCause(causeCreateDTO));
        verify(causeRepository, never()).save(any(Cause.class));
    }

    @Test
    void updateCause_ShouldReturnUpdatedCauseReadDTO() {
        when(causeRepository.findById(anyString())).thenReturn(Optional.of(cause));
        when(causeMapper.toDto(any(Cause.class))).thenReturn(causeReadDTO);
        when(causeRepository.save(any(Cause.class))).thenReturn(cause);
        doNothing().when(causeMapper).updateEntity(any(CauseUpdateDTO.class), any(Cause.class));

        CauseReadDTO result = causeService.updateCause("1", causeUpdateDTO);

        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(causeRepository).save(cause);
    }

    @Test
    void updateCause_ShouldThrowException_WhenCauseNotFound() {
        when(causeRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> causeService.updateCause("1", causeUpdateDTO));
        verify(causeRepository, never()).save(any(Cause.class));
    }

    @Test
    void getCause_ShouldReturnCauseReadDTO() {
        when(causeRepository.findById(anyString())).thenReturn(Optional.of(cause));
        when(causeMapper.toDto(any(Cause.class))).thenReturn(causeReadDTO);

        CauseReadDTO result = causeService.getCause("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
    }

    @Test
    void getCause_ShouldThrowException_WhenCauseNotFound() {
        when(causeRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> causeService.getCause("1"));
    }

    @Test
    void getAllCauses_ShouldReturnListOfCauseReadDTOs() {
        when(causeRepository.findAll()).thenReturn(List.of(cause));
        when(causeMapper.toDto(any(Cause.class))).thenReturn(causeReadDTO);

        List<CauseReadDTO> result = causeService.getAllCauses();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void deleteCause_ShouldDeleteCause_WhenExists() {
        when(causeRepository.existsById(anyString())).thenReturn(true);
        doNothing().when(causeRepository).deleteById(anyString());

        assertDoesNotThrow(() -> causeService.deleteCause("1"));
        verify(causeRepository).deleteById("1");
    }

    @Test
    void deleteCause_ShouldThrowException_WhenCauseNotFound() {
        when(causeRepository.existsById(anyString())).thenReturn(false);

        assertThrows(DataNotFoundException.class, () -> causeService.deleteCause("1"));
        verify(causeRepository, never()).deleteById(anyString());
    }
}
