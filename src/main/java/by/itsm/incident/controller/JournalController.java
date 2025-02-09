package by.itsm.incident.controller;

import by.itsm.exception.handler.ErrorResponse;
import by.itsm.incident.dto.JournalCreateDTO;
import by.itsm.incident.dto.JournalReadDTO;
import by.itsm.incident.dto.JournalUpdateDTO;
import by.itsm.incident.service.JournalService;
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
@RequestMapping("/api/journals")
@AllArgsConstructor
@Tag(name = "journals", description = "Операции с журналами инцидентов")
public class JournalController {
  private JournalService journalService;

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
              schema = @Schema(implementation = JournalReadDTO.class)))
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
  public ResponseEntity<JournalReadDTO> getJournal(@PathVariable String id) {
    return new ResponseEntity<>(journalService.getJournal(id), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  @Operation(
      summary = "Создать причину инцидента",
      description = "Этот эндпоинт позволяет создать новую причину инцидента.")
  @ApiResponse(
      responseCode = "201",
      description = "Инцидент успешно добавлен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = JournalReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<JournalReadDTO> createJournal(@RequestBody @Valid JournalCreateDTO dto) {
    return new ResponseEntity<>(journalService.createJournal(dto), HttpStatus.CREATED);
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
              schema = @Schema(implementation = JournalReadDTO.class)))
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
  public ResponseEntity<JournalReadDTO> updateJournal(
      @PathVariable String id, @RequestBody @Valid JournalUpdateDTO dto) {
    return new ResponseEntity<>(journalService.updateJournal(id, dto), HttpStatus.OK);
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
              schema = @Schema(implementation = JournalReadDTO.class)))
  public ResponseEntity<List<JournalReadDTO>> getAllJournals() {
    return ResponseEntity.ok(journalService.getAllJournals());
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
              schema = @Schema(implementation = JournalReadDTO.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Инцидент не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<Void> deleteJournal(@PathVariable String id) {
    journalService.deleteJournal(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
