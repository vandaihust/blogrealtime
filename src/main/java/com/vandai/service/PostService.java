package com.vandai.service;

import java.util.List;

import com.vandai.dto.PostDto;
import com.vandai.dto.PostResponse;
import com.vandai.entity.Post;

public interface PostService {
	 PostDto createPost(PostDto postDto);
	 PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
	 PostDto getPostById(Long id);
	 PostDto updatePost(PostDto postDto, Long id);
	 String deletePost(Long id);
	
}
