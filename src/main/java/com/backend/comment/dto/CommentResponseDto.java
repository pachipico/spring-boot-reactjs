package com.backend.comment.dto;

import com.backend.comment.domain.Comment;
import com.backend.user.domain.User;
import com.backend.user.dto.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@AllArgsConstructor
public class CommentResponseDto {
	private Long cId;
	
	private String nickName;
	private String regAt;
	private String modAt;
	private String content;
	private Long bId;
	private UserResponseDto writer;
	
	
	public CommentResponseDto(Comment comment, User user) {
		this.cId = comment.getCId();
		this.nickName = comment.getNickName();
		this.regAt = comment.getRegAt();
		this.modAt = comment.getModAt();
		this.content = comment.getContent();
		this.bId = comment.getBId();
		this.writer = new UserResponseDto(user);
		
	}
	
	
}
