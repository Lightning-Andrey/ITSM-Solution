package by.itsm.chat.controller;

import by.itsm.chat.dto.ChatCreateDTO;
import by.itsm.chat.dto.ChatReadDTO;
import by.itsm.chat.dto.ChatUpdateDTO;
import by.itsm.chat.service.ChatService;
import by.itsm.exception.handler.ErrorResponse;
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
@RequestMapping("/api/chats")
@AllArgsConstructor
@Tag(name = "chats", description = "Операции с чатами")
public class ChatController {
  private ChatService chatService;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("{id}")
  @Operation(
      summary = "Получить информацию об чате",
      description =
          "Этот эндпоинт позволяет получить информацию об чате по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Чат успешно получен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Чат не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<ChatReadDTO> getChat(@PathVariable String id) {
    return new ResponseEntity<>(chatService.getChat(id), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  @Operation(summary = "Создать чат", description = "Этот эндпоинт позволяет создать новый чат.")
  @ApiResponse(
      responseCode = "201",
      description = "Чат успешно добавлен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<ChatReadDTO> createChat(@RequestBody @Valid ChatCreateDTO dto) {
    return new ResponseEntity<>(chatService.createChat(dto), HttpStatus.CREATED);
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping("/{id}")
  @Operation(
      summary = "Обновить информацию об чате",
      description =
          "Этот эндпоинт позволяет обновить информацию об чате по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Чат успешно обновлен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Чат не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<ChatReadDTO> updateChat(
      @PathVariable String id, @RequestBody @Valid ChatUpdateDTO dto) {
    return new ResponseEntity<>(chatService.updateChat(id, dto), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping
  @Operation(
      summary = "Получить список всех чатов",
      description = "Этот эндпоинт позволяет получить информацию о всех чатах.")
  @ApiResponse(
      responseCode = "200",
      description = "Успех.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatReadDTO.class)))
  public ResponseEntity<List<ChatReadDTO>> getAllChats() {
    return ResponseEntity.ok(chatService.getAllChats());
  }

  @PreAuthorize("isAuthenticated()")
  @DeleteMapping("/{id}")
  @Operation(summary = "Удалить чат", description = "Этот эндпоинт позволяет удалить чат.")
  @ApiResponse(
      responseCode = "204",
      description = "Чат успешно удален.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatReadDTO.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Чат не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<Void> deleteChat(@PathVariable String id) {
    chatService.deleteChat(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
