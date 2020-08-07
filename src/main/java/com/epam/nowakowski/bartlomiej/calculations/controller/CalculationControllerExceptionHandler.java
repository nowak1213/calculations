package com.epam.nowakowski.bartlomiej.calculations.controller;

import com.epam.nowakowski.bartlomiej.calculations.exception.DecimalDeserializationException;
import com.epam.nowakowski.bartlomiej.calculations.exception.MissingFieldsDeserializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CalculationControllerExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(value = DecimalDeserializationException.class)
   public ResponseEntity<Object> handleDecimalDeserializationException(DecimalDeserializationException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
   }

   @ExceptionHandler(value = MissingFieldsDeserializationException.class)
   public ResponseEntity<Object> handleMissingFieldsDeserializationException(MissingFieldsDeserializationException ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
   }
}
