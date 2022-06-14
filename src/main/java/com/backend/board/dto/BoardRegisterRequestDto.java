package com.backend.board.dto;

import com.backend.board.domain.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegisterRequestDto {
	private String title;
	private String writer;
	private String content;
	
	public Board toEntity () {
		return new Board(this.title, this.writer, this.content);
	}
}
