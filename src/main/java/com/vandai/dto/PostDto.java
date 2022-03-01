package com.vandai.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.vandai.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private Long id;
//	title not null or empty
//	title should have at least 2 characters
	@NotEmpty
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;
	@NotEmpty
	@Size(min = 2, message = "Post description should have at least 10 characters")
	private String description;
	@NotEmpty
	private String content;
	private Set<Comment> comments;

}
