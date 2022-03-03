package com.vandai.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto {
	private String name;
	private String email;
	private String username;
	private String password;
}
