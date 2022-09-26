package com.example.mensajesespias.infrastructure.config.spring.exception;

import com.example.mensajesespias.domain.network.CommunicationNetworkNotExists;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  private static final String OBJECT_NOT_FOUND = "Object not found in database.";
  private static final String ERROR_OCCURS = "Application has encountered an error.";

  private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message,
      Exception e) {
    return new ErrorResponse(httpStatus.value(), message, e.getMessage());
  }

  private static ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message,
      List<String> moreInfo) {
    return new ErrorResponse(httpStatus.value(), message, moreInfo);
  }

  @ExceptionHandler(value = Exception.class)
  protected ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        ERROR_OCCURS,
        e);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = CommunicationNetworkNotExists.class)
  protected ResponseEntity<ErrorResponse> handleCommunicationNetworkNotExists(Exception e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST,
        OBJECT_NOT_FOUND,
        e);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}