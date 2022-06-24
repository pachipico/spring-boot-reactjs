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
public class CommentModifyRequestDto {
	private Long cId;
	private String content;
	
	public Comment toEntity () {
		return new Comment(this.cId, this.content);
	}
}
