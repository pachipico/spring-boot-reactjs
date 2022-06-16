package com.backend.board.dto;

import com.backend.board.domain.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardModifyRequestDto {
	private String title;
	private String content;
	private String category;
	
	public Board toEntity () {
		return new Board(this.title, this.content, this.category);
	}
}
