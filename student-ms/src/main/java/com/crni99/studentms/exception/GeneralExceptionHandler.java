package com.crni99.studentms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(value = { EmptyInputException.class })
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException) {

		return new ResponseEntity<String>(emptyInputException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { NoSuchElementException.class })
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {

		return new ResponseEntity<String>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { EmailInUseException.class })
	public ResponseEntity<String> handleEmailInUseException(EmailInUseException emailInUseException) {

		return new ResponseEntity<String>(emailInUseException.getMessage(), HttpStatus.IM_USED);
	}

}
