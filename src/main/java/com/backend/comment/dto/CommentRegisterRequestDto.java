package com.backend.comment.dto;

import com.backend.comment.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentRegisterRequestDto {
	private String writer;
	private String content;
	private Long bId;
	
	public Comment toEntity() {
		return new Comment(this.bId, this.writer, this.content);
	}
}
