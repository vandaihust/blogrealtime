package com.vandai.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandai.dto.LoginDto;
import com.vandai.dto.SignUpDto;
import com.vandai.service.AuthService;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	@Autowired
	AuthService authService;

	@PostMapping("signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
		return new ResponseEntity<>(authService.authenticateUser(loginDto), HttpStatus.OK);
	}

	@PostMapping("signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
		Map.Entry<String, HttpStatus> entry = authService.registerUser(signUpDto).entrySet().iterator().next();
		String msg = entry.getKey();
		HttpStatus status = entry.getValue();
		return new ResponseEntity<>(msg, status);
	}
}
