package com.groldan.spring.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	  @ExceptionHandler(ErrorException.class)
	  public ResponseEntity<Map<String, String>> handleException(ErrorException e) {
		  Map<String, String> errorResponse = new HashMap<>();

		    errorResponse.put("message", e.getLocalizedMessage());		    
		    return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED);
	  }
	  
	  @ExceptionHandler(ValidationException.class)
	  public ResponseEntity<Map<String, String>> handleException(ValidationException e) {
		  Map<String, String> errorResponse = new HashMap<>();

		    errorResponse.put("message", e.getLocalizedMessage());		    
		    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	  }
}
