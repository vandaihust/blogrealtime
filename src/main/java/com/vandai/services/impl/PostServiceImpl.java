package com.vandai.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vandai.dto.PostDto;
import com.vandai.dto.PostResponse;
import com.vandai.entity.Post;
import com.vandai.exeption.ResourceNotFoundExeption;
import com.vandai.repository.PostRepository;
import com.vandai.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostRepository postRepository;
	@Autowired
	ModelMapper modelMapper;
	//convert dto to entity
	 private PostDto mapToDto(Post post) {
//		 return new PostDto(post.getId(), post.getTitle(), post.getDescription(), post.getContent());
		 return modelMapper.map(post, PostDto.class);
	 }
	 private Post mapToEntity(PostDto postDto) {
//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setContent(postDto.getContent());
//		post.setDescription(postDto.getDescription());
		 Post post = modelMapper.map(postDto, Post.class);
		return post;
	 }
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		Post newPost = postRepository.save(post);
		PostDto postResponse = mapToDto(newPost);
		return postResponse;
	}
	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> postPage = postRepository.findAll(pageable);
		List<Post> listPost = postPage.getContent();
		List<PostDto> listPostDto = listPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(listPostDto);
		postResponse.setPageNo(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalElements(postPage.getTotalElements());
		postResponse.setTotalPages(postPage.getTotalPages());
		postResponse.setLast(postPage.isLast());
		return postResponse;
		
		
	}
	@Override
	public PostDto getPostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Post", "id", id));
		return mapToDto(post);
	}
	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		Post updatePost = postRepository.save(post);
		return mapToDto(updatePost);
	}
	@Override
	public String deletePost(Long id) {
		postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("Post", "id", id));
		if(postRepository.existsById(id)) {
			postRepository.deleteById(id);
			return "Post entity delete successfully";
		}
		return "Post entity delete fail";
	}

}
