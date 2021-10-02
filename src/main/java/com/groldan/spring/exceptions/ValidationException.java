package com.groldan.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class ValidationException extends RuntimeException implements IErrorException{
	public static final String SERVER_NOT_RESPONDING_MSG = "Service Not Available";
	public ValidationException(Throwable throwable) {
	    super(SERVER_NOT_RESPONDING_MSG, throwable);
	    this.errorCode = "0";
	  }

	  public ValidationException(String message, String errorCode) {
	    super(message);
	    this.errorCode = errorCode;
	  }

	  @JsonProperty("errorCode")
	  @NotNull
	  private String errorCode;

	  @Override
	  @JsonProperty("errorCode")
	  public String getErrorCode() {
	    return errorCode;
	  }

	  @JsonProperty("errorCode")
	  public void setErrorCode(String errorCode) {
	    this.errorCode = errorCode;
	  }	  
	
}
