package com.vandai.exeption;

import org.springframework.http.HttpStatus;

public class BlogAPIExeption extends RuntimeException{
	private HttpStatus status;
	private String message;

	public BlogAPIExeption(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public BlogAPIExeption() {
		super();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
