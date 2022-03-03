package com.vandai.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vandai.dto.JwtAuthResponse;
import com.vandai.dto.LoginDto;
import com.vandai.dto.SignUpDto;
import com.vandai.entity.Role;
import com.vandai.entity.User;
import com.vandai.repository.RoleRepository;
import com.vandai.repository.UserRepository;
import com.vandai.security.JwtTokenProvider;
import com.vandai.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenProvider tokenProvider;
	@Override
	public JwtAuthResponse authenticateUser(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//generate token
		String token = tokenProvider.generateToken(authentication);
		return new JwtAuthResponse(token);
	}

	@Override
	public Map<String, HttpStatus> registerUser(SignUpDto signUpDto) {
		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			Map<String, HttpStatus> ht = new HashMap<>();
			ht.put("User is ready taken", HttpStatus.BAD_REQUEST);
			return ht;
		}
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			Map<String, HttpStatus> ht = new HashMap<>();
			ht.put("Email is ready taken", HttpStatus.BAD_REQUEST);
			return ht;
		}
		// create new User
		User user = new User();
		user.setEmail(signUpDto.getEmail());
		user.setUsername(signUpDto.getUsername());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		Role role = roleRepository.findByName("ROLE_ADMIN");
		user.setRoles(Collections.singleton(role));// setRole and except ADMIN
		userRepository.save(user);
		Map<String, HttpStatus> ht = new HashMap<>();
		ht.put("User registed successfully", HttpStatus.OK);
		return ht;
	}

}
