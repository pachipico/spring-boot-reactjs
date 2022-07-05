package com.backend.comment.dto;

import com.backend.board.domain.Board;
import com.backend.comment.domain.Comment;
import com.backend.user.domain.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@ToString
public class UserWroteCommentDto extends CommentResponseDto {
	private Board board;
	
	public UserWroteCommentDto(Comment comment, User user) {
		super(comment, user);
	}

	public UserWroteCommentDto(Comment comment, User user, Board board) {
		super(comment, user);
		this.board = board;
	}
	
}
