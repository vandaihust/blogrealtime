package com.vandai.service;

import java.util.List;

import com.vandai.dto.CommentDto;

public interface CommentService {
	CommentDto createComment(Long postId, CommentDto commentDto);
	List<CommentDto> getAllComments(Long postId);
	CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
	String deletePost(Long post, Long id);
}
