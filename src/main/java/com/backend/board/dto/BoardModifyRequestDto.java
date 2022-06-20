package com.backend.board.dto;

import com.backend.board.domain.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardModifyRequestDto {
	private Long bId;
	private String title;
	private String content;
	private String category;
	
	public Board toEntity () {
		return new Board(this.bId, this.title, this.content, this.category);
	}
}
