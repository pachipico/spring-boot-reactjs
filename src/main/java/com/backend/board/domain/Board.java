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
	private String category;
	private int likeCnt;
	
	
	/**
	 * 글 작성에 사용될 생성자.
	 * @param title
	 * @param writer
	 * @param content
	 * @param category
	 * @param siName
	 */
	public Board(String title, String writer, String content, String category, String siName) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.category = category;
		this.siName = siName;
	}

	/**
	 * 글 수정에 사용될 생성자.
	 * @param title
	 * @param content
	 * @param category
	 */
	public Board(String title, String content, String category) {
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	
	
}
