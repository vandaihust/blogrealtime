package com.vandai.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vandai.dto.CommentDto;
import com.vandai.entity.Comment;
import com.vandai.entity.Post;
import com.vandai.exception.BlogAPIException;
import com.vandai.exception.ResourceNotFoundException;
import com.vandai.repository.CommentRepository;
import com.vandai.repository.PostRepository;
import com.vandai.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	ModelMapper modelMapper;

	private CommentDto mapToDto(Comment comment) {
//		return new CommentDto(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
		return modelMapper.map(comment, CommentDto.class);
	}

	private Comment mapToEntity(CommentDto commentDto) {
//		Comment comment = new Comment();
//		comment.setName(commentDto.getName());
//		comment.setBody(commentDto.getBody());
//		comment.setEmail(commentDto.getEmail());
		return modelMapper.map(commentDto, Comment.class);
	}

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment newComment = mapToEntity(commentDto);
		newComment.setPost(post);
		Comment commentResponse = commentRepository.save(newComment);
		return mapToDto(commentResponse);

	}

	@Override
	public List<CommentDto> getAllComments(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Set<Comment> listOfComment = post.getComments();
		List<CommentDto> listOfCommentDto = listOfComment.stream().map((comment) -> mapToDto(comment))
				.collect(Collectors.toList());
		return listOfCommentDto;
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
		Comment updateComment = commentRepository.save(comment);
		return mapToDto(updateComment);
	}

	@Override
	public String deleteComment(Long postId, Long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		commentRepository.deleteById(commentId);
		String msg = "Comment is deleted successfully";
		return msg;
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return mapToDto(comment);
	}

}
