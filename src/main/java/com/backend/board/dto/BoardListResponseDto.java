package com.backend.board.dto;

import com.backend.board.domain.Board;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardListResponseDto {
	private Long bId;
	private String siName;
	private String title;
	private String writer;
	private String nickName;
	private String regAt;
	private String modAt;
	private int hit;
	private String category;
	private int likeCnt;

	public BoardListResponseDto(Board board) {
		this.bId = board.getBId();
		this.siName = board.getSiName();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.nickName = board.getNickName();
		this.regAt = board.getRegAt();
		this.modAt = board.getModAt();
		this.hit = board.getHit();
		this.category = board.getCategory();
		this.likeCnt = board.getLikeCnt();
	}

}
