package com.vandai.dto;

import lombok.Data;
@Data
public class PostDto {
	private Long id;
	private String title;
	private String description;
	private String content;
	public PostDto(Long id, String title, String description, String content) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
	}
	
}
