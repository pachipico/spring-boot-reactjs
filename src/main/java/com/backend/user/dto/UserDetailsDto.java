package com.backend.user.dto;

import java.util.List;

import com.backend.board.dto.BoardListResponseDto;
import com.backend.user.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDetailsDto extends UserResponseDto {
	
	private List<BoardListResponseDto> boards;
	
	public UserDetailsDto(User user, List<BoardListResponseDto> boards) {
		super(user);
		this.boards = boards;
	}

}
