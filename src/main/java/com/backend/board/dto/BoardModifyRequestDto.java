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
	
	public Board toEntity () {
		return new Board(title, content);
	}
}
