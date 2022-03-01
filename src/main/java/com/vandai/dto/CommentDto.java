package com.vandai.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;
	@NotEmpty(message = "Name should be not empty or null")
	private String name;
	@NotEmpty(message = "Email should be not null or empty")
	@Email
	private String email;
	@NotEmpty
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
	private String body;
}
