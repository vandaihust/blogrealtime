package com.vandai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandai.config.SecuredRestController;
import com.vandai.dto.CommentDto;
import com.vandai.service.CommentService;

@RestController
@RequestMapping("api")
public class CommentController implements SecuredRestController{
	@Autowired
	CommentService commentService;

	@PostMapping("posts/{postId}/comments")
	public ResponseEntity<?> createComment(@PathVariable long postId, @Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("posts/{postId}/comments")
	public ResponseEntity<?> getAllComments(@PathVariable long postId) {
		return new ResponseEntity<>(commentService.getAllComments(postId), HttpStatus.OK);
	}

	@GetMapping("posts/{postId}/comments/{commentId}")
	public ResponseEntity<?> getAllComments(@PathVariable long postId, @PathVariable long commentId) {
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("posts/{postId}/comments/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable long postId, @PathVariable long commentId,
			@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
	}

	@DeleteMapping("posts/{postId}/comments/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable long postId, @PathVariable long commentId) {
		return new ResponseEntity<>(commentService.deleteComment(postId, commentId), HttpStatus.OK);
	}
}
