package by.itsm.handler;

import by.itsm.exception.DataAlreadyExistsException;
import by.itsm.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleDataAlreadyExistsException(
      DataAlreadyExistsException ex) {
    return new ResponseEntity<>(
        ErrorResponse.builder()
            .errorCode(ErrorCode.DATA_ALREADY_EXISTS)
            .message(ex.getMessage())
            .build(),
        HttpStatus.CONFLICT);
  }

  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex) {
    return new ResponseEntity<>(
        ErrorResponse.builder()
            .errorCode(ErrorCode.DATA_NOT_FOUND)
            .message(ex.getMessage())
            .build(),
        HttpStatus.NOT_FOUND);
  }
}
