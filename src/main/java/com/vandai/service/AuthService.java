package com.vandai.service;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.vandai.dto.JwtAuthResponse;
import com.vandai.dto.LoginDto;
import com.vandai.dto.SignUpDto;

public interface AuthService {
	JwtAuthResponse authenticateUser(LoginDto loginDto);

	Map<String, HttpStatus> registerUser(SignUpDto signUpDto);
}
