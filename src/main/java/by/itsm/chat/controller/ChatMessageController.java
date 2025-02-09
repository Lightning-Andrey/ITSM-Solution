package by.itsm.chat.controller;

import by.itsm.chat.dto.ChatMessageCreateUpdateDTO;
import by.itsm.chat.dto.ChatMessageReadDTO;
import by.itsm.chat.service.ChatMessageService;
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
@RequestMapping("/api/chat/{chatId}/messages")
@AllArgsConstructor
@Tag(name = "chat messages", description = "Операции с сообщениями в чате")
public class ChatMessageController {
  private ChatMessageService chatMessageService;

  @PreAuthorize("isAuthenticated()")
  @GetMapping("{id}")
  @Operation(
      summary = "Получить информацию об сообщение чата",
      description =
          "Этот эндпоинт позволяет получить информацию об сообщение чата по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Сообщение Чата успешно получен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatMessageReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Сообщение Чата не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<ChatMessageReadDTO> getChatMessage(
      @PathVariable String chatId, @PathVariable String id) {
    return new ResponseEntity<>(chatMessageService.getChatMessage(id), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping
  @Operation(
      summary = "Создать сообщение чата",
      description = "Этот эндпоинт позволяет создать новый сообщение чата.")
  @ApiResponse(
      responseCode = "201",
      description = "Сообщение Чата успешно добавлен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatMessageReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<ChatMessageReadDTO> createChatMessage(
      @PathVariable String chatId, @RequestBody @Valid ChatMessageCreateUpdateDTO dto) {
    return new ResponseEntity<>(chatMessageService.createChatMessage(dto), HttpStatus.CREATED);
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping("/{id}")
  @Operation(
      summary = "Обновить информацию об сообщение чата",
      description =
          "Этот эндпоинт позволяет обновить информацию об сообщение чата по его уникальному идентификатору.")
  @ApiResponse(
      responseCode = "200",
      description = "Сообщение Чата успешно обновлен.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatMessageReadDTO.class)))
  @ApiResponse(
      responseCode = "403",
      description = "Неверный формат запроса или ошибка валидации.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Сообщение Чата не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<ChatMessageReadDTO> updateChatMessage(
      @PathVariable String chatId,
      @PathVariable String id,
      @RequestBody @Valid ChatMessageCreateUpdateDTO dto) {
    return new ResponseEntity<>(chatMessageService.updateChatMessage(id, dto), HttpStatus.OK);
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping
  @Operation(
      summary = "Получить список всех сообщение чата",
      description = "Этот эндпоинт позволяет получить информацию о всех сообщение чатах.")
  @ApiResponse(
      responseCode = "200",
      description = "Успех.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatMessageReadDTO.class)))
  public ResponseEntity<List<ChatMessageReadDTO>> getAllChatMessages(@PathVariable String chatId) {
    return ResponseEntity.ok(chatMessageService.getAllChatMessages());
  }

  @PreAuthorize("isAuthenticated()")
  @DeleteMapping("/{id}")
  @Operation(
      summary = "Удалить сообщение чата",
      description = "Этот эндпоинт позволяет удалить сообщение чата.")
  @ApiResponse(
      responseCode = "204",
      description = "Сообщение Чата успешно удален.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ChatMessageReadDTO.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Сообщение Чата не найден.",
      content =
          @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = ErrorResponse.class)))
  public ResponseEntity<Void> deleteChatMessage(
      @PathVariable String chatId, @PathVariable String id) {
    chatMessageService.deleteChatMessage(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
