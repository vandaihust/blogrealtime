package com.vandai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vandai.dto.CommentDto;
import com.vandai.service.CommentService;

@RestController
@RequestMapping("api")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@PostMapping("posts/{postId}/comment")
	public ResponseEntity<?> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
}
