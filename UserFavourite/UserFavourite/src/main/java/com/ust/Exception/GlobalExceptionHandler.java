package com.ust.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {
	@ExceptionHandler(RestuarantNotFoundException.class)
	public ResponseEntity<String> RestuarantNotFoundException(RestuarantNotFoundException e) {
		return new ResponseEntity<>("Favourite Item Not Found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RestuarantAlreadyExistsException.class)
	public ResponseEntity<String>RestuarantAlreadyExistsException(RestuarantAlreadyExistsException e) {
		return new ResponseEntity<>("Favourite Item  Already Exists", HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String>UserNotFoundException(UserNotFoundException e) {
		return new ResponseEntity<>("USER NOT FOUND", HttpStatus.CONFLICT);
	}
}


