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
	
	private List<BoardListResponseDto> boards;
	
	private List<CommentResponseDto> comments;
	
	public UserDetailsDto(User user, List<BoardListResponseDto> boards, List<CommentResponseDto> comments) {
		super(user);
		this.boards = boards;
		this.comments = comments;
	}

}
