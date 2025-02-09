package by.itsm.incident.controller;

import by.itsm.exception.handler.ErrorResponse;
import by.itsm.incident.dto.IncidentCreateDTO;
import by.itsm.incident.dto.IncidentReadDTO;
import by.itsm.incident.dto.IncidentUpdateDTO;
import by.itsm.incident.service.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
@AllArgsConstructor
@Tag(name = "incidents", description = "Операции с инцидентами")
public class IncidentController {
  private IncidentService incidentService;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("{id}")
  @Operation(
      summary = "Получить информацию об инциденте",
      description =
          "Этот эндпоинт позволяет получить информацию об инциденте по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Инцидент успешно получен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = IncidentReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Инцидент не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<IncidentReadDTO> getIncident(@PathVariable String id) {
    return new ResponseEntity<>(incidentService.getIncident(id), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  @Operation(
      summary = "Создать инцидент",
      description = "Этот эндпоинт позволяет создать новый инцидент.")
  @ApiResponse(
      responseCode = "201",
      description = "Инцидент успешно добавлен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = IncidentReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<IncidentReadDTO> createIncident(@RequestBody @Valid IncidentCreateDTO dto) {
    return new ResponseEntity<>(incidentService.createIncident(dto), HttpStatus.CREATED);
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping("/{id}")
  @Operation(
      summary = "Обновить информацию об инциденте",
      description =
          "Этот эндпоинт позволяет обновить информацию об инциденте по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Инцидент успешно обновлен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = IncidentReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Инцидент не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<IncidentReadDTO> updateIncident(
      @PathVariable String id, @RequestBody @Valid IncidentUpdateDTO dto) {
    return new ResponseEntity<>(incidentService.updateIncident(id, dto), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping
  @Operation(
      summary = "Получить список всех инцидентов",
      description = "Этот эндпоинт позволяет получить информацию о всех инцидентах.")
  @ApiResponse(
      responseCode = "200",
      description = "Успех.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = IncidentReadDTO.class)))
  public ResponseEntity<List<IncidentReadDTO>> getAllIncidents() {
    return ResponseEntity.ok(incidentService.getAllIncidents());
  }

  @PreAuthorize("isAuthenticated()")
  @DeleteMapping("/{id}")
  @Operation(
      summary = "Удалить инцидент",
      description = "Этот эндпоинт позволяет удалить инцидент.")
  @ApiResponse(
      responseCode = "204",
      description = "Инцидент успешно удален.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = IncidentReadDTO.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Инцидент не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<Void> deleteIncident(@PathVariable String id) {
    incidentService.deleteIncident(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
