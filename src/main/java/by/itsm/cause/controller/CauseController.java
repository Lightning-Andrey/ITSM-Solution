package by.itsm.cause.controller;

import by.itsm.cause.dto.CauseCreateDTO;
import by.itsm.cause.dto.CauseReadDTO;
import by.itsm.cause.dto.CauseUpdateDTO;
import by.itsm.cause.service.CauseService;
import by.itsm.exception.handler.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/causes")
@AllArgsConstructor
public class CauseController {
    private CauseService causeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}")
    @Operation(
            summary = "Получить информацию об причине инцидента",
            description =
                    "Этот эндпоинт позволяет получить информацию об причине инцидента по его уникальному идентификатору.")
    @ApiResponse(
            responseCode = "200",
            description = "Причина инцидента успешно получено.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CauseReadDTO.class)))
    @ApiResponse(
            responseCode = "403",
            description = "Неверный формат запроса или ошибка валидации.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Причина инцидента не найдено.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<CauseReadDTO> getCause(@PathVariable String id) {
        return new ResponseEntity<>(causeService.getCause(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    @Operation(
            summary = "Создать причина инцидента больницы",
            description = "Этот эндпоинт позволяет создать новое причина инцидента больницы.")
    @ApiResponse(
            responseCode = "201",
            description = "Причина инцидента успешно добавлено.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CauseReadDTO.class)))
    @ApiResponse(
            responseCode = "403",
            description = "Неверный формат запроса или ошибка валидации.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(
            responseCode = "409",
            description = "Причина инцидента с таким названием уже существует.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<CauseReadDTO> createCause(
            @RequestBody @Valid CauseCreateDTO dto) {
        return new ResponseEntity<>(causeService.createCause(dto), HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    @Operation(
            summary = "Обновить информацию об причине инцидента",
            description =
                    "Этот эндпоинт позволяет обновить информацию об причине инцидента по его уникальному идентификатору.")
    @ApiResponse(
            responseCode = "200",
            description = "Причина инцидента успешно обновлено.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CauseReadDTO.class)))
    @ApiResponse(
            responseCode = "403",
            description = "Неверный формат запроса или ошибка валидации.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Причина инцидента не найдено.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<CauseReadDTO> updateCause(
            @PathVariable String id, @RequestBody @Valid CauseUpdateDTO dto) {
        return new ResponseEntity<>(causeService.updateCause(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    @Operation(
            summary = "Получить список всех причин инцидентов",
            description = "Этот эндпоинт позволяет получить информацию о всех причинах инцидентов.")
    @ApiResponse(
            responseCode = "200",
            description = "Успех.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CauseReadDTO.class)))
    public ResponseEntity<List<CauseReadDTO>> getAllCauses() {
        return ResponseEntity.ok(causeService.getAllCauses());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить причина инцидента",
            description = "Этот эндпоинт позволяет удалить причина инцидента.")
    @ApiResponse(
            responseCode = "204",
            description = "Причина инцидента успешно удалено.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CauseReadDTO.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Причина инцидента не найдено.",
            content =
            @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<Void> deleteSpeciality(@PathVariable String id) {
        causeService.deleteCause(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
