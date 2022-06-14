package com.backend.board.domain;

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
public class Board {
	private Long bId;
	private String title;
	private String siName;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	private int hit;
	
	
	/**
	 * 글 작성 dto에 사용될 생성자.
	 * @param title
	 * @param writer
	 * @param content
	 */
	public Board(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	/**
	 * 글 수정 dto에 사용될 생성자.
	 * @param title
	 * @param content
	 */
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	
	
}
