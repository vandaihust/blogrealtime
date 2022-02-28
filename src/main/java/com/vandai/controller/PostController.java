package com.vandai.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandai.dto.PostDto;
import com.vandai.service.PostService;
import com.vandai.utils.AppContants;


@RestController
@RequestMapping("api/posts")
public class PostController {
	@Autowired
	PostService postService;
	@PostMapping
	public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<?> getAllPost(
			@RequestParam(value="pageNo", required = false, defaultValue = AppContants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value="pageSize", defaultValue = AppContants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppContants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppContants.DEFAULT_SORT_DIR, required = false) String sortDir
			) {
		return new ResponseEntity<>(postService.getAllPost(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getPostById(@PathVariable Long id) {
		return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> getPostById(@PathVariable Long id, @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id) {
		return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
	}
}
