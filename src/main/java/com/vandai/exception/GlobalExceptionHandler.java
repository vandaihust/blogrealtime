package com.vandai.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.vandai.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	// handle specific exeptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundExeption(ResourceNotFoundException exeption,
			WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exeption.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException exeption,
			WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exeption.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
//	global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exeption,
			WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exeption.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
