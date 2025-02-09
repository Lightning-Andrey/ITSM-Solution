package by.itsm.solution.controller;

import by.itsm.exception.handler.ErrorResponse;
import by.itsm.solution.dto.SolutionCreateDTO;
import by.itsm.solution.dto.SolutionReadDTO;
import by.itsm.solution.dto.SolutionUpdateDTO;
import by.itsm.solution.service.SolutionService;
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
@RequestMapping("/api/solutions")
@AllArgsConstructor
@Tag(name = "solutions", description = "Операции с решениями решений")
public class SolutionController {
  private SolutionService solutionService;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("{id}")
  @Operation(
      summary = "Получить информацию о решении",
      description =
          "Этот эндпоинт позволяет получить информацию о решении по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Решениеуспешно получен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = SolutionReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Решение найдено.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<SolutionReadDTO> getSolution(@PathVariable String id) {
    return new ResponseEntity<>(solutionService.getSolution(id), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  @Operation(
      summary = "Создать решение",
      description = "Этот эндпоинт позволяет создать новое решение.")
  @ApiResponse(
      responseCode = "201",
      description = "Решение успешно добавлено.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = SolutionReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<SolutionReadDTO> createSolution(@RequestBody @Valid SolutionCreateDTO dto) {
    return new ResponseEntity<>(solutionService.createSolution(dto), HttpStatus.CREATED);
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping("/{id}")
  @Operation(
      summary = "Обновить информацию о решении",
      description =
          "Этот эндпоинт позволяет обновить информацию о решении по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Решениеуспешно обновлено.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = SolutionReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Решение найдено.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<SolutionReadDTO> updateSolution(
      @PathVariable String id, @RequestBody @Valid SolutionUpdateDTO dto) {
    return new ResponseEntity<>(solutionService.updateSolution(id, dto), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping
  @Operation(
      summary = "Получить список всех решений",
      description = "Этот эндпоинт позволяет получить информацию о всех решениях.")
  @ApiResponse(
      responseCode = "200",
      description = "Успех.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = SolutionReadDTO.class)))
  public ResponseEntity<List<SolutionReadDTO>> getAllSolutions() {
    return ResponseEntity.ok(solutionService.getAllSolutions());
  }

  @PreAuthorize("isAuthenticated()")
  @DeleteMapping("/{id}")
  @Operation(summary = "Удалить решение", description = "Этот эндпоинт позволяет удалить решение.")
  @ApiResponse(
      responseCode = "204",
      description = "Решение успешно удалено.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = SolutionReadDTO.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Решение не найдено.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<Void> deleteSolution(@PathVariable String id) {
    solutionService.deleteSolution(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
