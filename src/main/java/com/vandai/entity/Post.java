package com.vandai.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
//title always not the same;
@Entity
@Table(
		name="post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
		)
@Getter
@Setter
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "content", nullable = false)
	private String content;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments = new HashSet<Comment>();
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
}
//orphanRemoval is an entirely ORM-specific thing. It marks "child" entity to be removed when it's no longer 
//referenced from the "parent" entity, e.g.
//when you remove the child entity from the corresponding collection of the parent entity.
