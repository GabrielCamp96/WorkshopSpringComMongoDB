package com.gabrielcamp.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

import com.gabrielcamp.workshopmongo.domain.Comment;

public class CommentDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String text;
	private Date date;

	public CommentDTO() {
		
	}
	
	public CommentDTO(Comment comment) {
		this.text = comment.getText();
		this.date = comment.getDate();
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
	
	
}
