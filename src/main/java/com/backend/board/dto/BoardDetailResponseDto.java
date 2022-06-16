package com.backend.board.dto;

import com.backend.board.domain.Board;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString

public class BoardDetailResponseDto {
	private Long bId;
	private String siName;
	private String title;
	private String writer;
	private int hit;
	private String content;
	private String regAt;
	private String modAt;
	private String category;
	private int likeCnt;

	public BoardDetailResponseDto(Board board) {
		this.bId = board.getBId();
		this.siName = board.getSiName();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.hit = board.getHit();
		this.content = board.getContent();
		this.regAt = board.getRegAt();
		this.modAt = board.getModAt();
		this.category = board.getCategory();
		this.likeCnt = board.getLikeCnt();
	}
}
