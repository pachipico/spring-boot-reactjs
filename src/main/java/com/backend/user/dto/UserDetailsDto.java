package com.backend.user.dto;

import java.util.List;

import com.backend.board.dto.BoardListResponseDto;
import com.backend.comment.dto.CommentResponseDto;
import com.backend.user.domain.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class UserDetailsDto extends UserResponseDto {
	
	private int boardCnt;
	
	private int commentCnt;
	
	public UserDetailsDto(User user,int boardCnt, int commentCnt) {
		super(user);
		this.boardCnt = boardCnt;
		this.commentCnt = commentCnt;
	}

}
