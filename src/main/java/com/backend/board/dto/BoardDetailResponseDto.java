package com.backend.board.dto;

import com.backend.board.domain.Board;
import com.backend.user.domain.User;
import com.backend.user.dto.UserResponseDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class BoardDetailResponseDto {
	private Long bId;
	private String siName;
	private String title;
	private UserResponseDto writer;
	private int hit;
	private String content;
	private String regAt;
	private String modAt;
	private String category;
	private int likeCnt;

	public BoardDetailResponseDto(Board board, User user) {
		this.bId = board.getBId();
		this.siName = board.getSiName();
		this.title = board.getTitle();
		this.writer = new UserResponseDto(user);
		this.hit = board.getHit();
		this.content = board.getContent();
		this.regAt = board.getRegAt();
		this.modAt = board.getModAt();
		this.category = board.getCategory();
		this.likeCnt = board.getLikeCnt();
	}
}
