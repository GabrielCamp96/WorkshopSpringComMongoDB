package com.gabrielcamp.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

import com.gabrielcamp.workshopmongo.domain.Comment;

public class CommentDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String text;
	private Date date;
	private AuthorDTO author;
	
	public CommentDTO() {
		
	}
	
	public CommentDTO(Comment comment) {
		super();
		this.id = comment.getId();
		this.text = comment.getText();
		this.date = comment.getDate();
		this.author = comment.getAuthor();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public AuthorDTO getAuthor() {
		return author;
	}
	
	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
