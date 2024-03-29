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
	private String nickName;
	private String content;
	private String regAt;
	private String modAt;
	private int hit;
	private String category;
	private String img;
	private int likeCnt;
	private int commentCnt;
	
	
	/**
	 * 글 작성에 사용될 생성자.
	 * @param title
	 * @param writer
	 * @param content
	 * @param category
	 * @param siName
	 * @param img
	 */
	public Board(String title, String writer, String content, String category, String siName, String img) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.category = category;
		this.siName = siName;
		this.img = img;
	}

	/**
	 * 글 수정에 사용될 생성자.
	 * @param bId
	 * @param title
	 * @param content
	 * @param category
	 * @param img
	 */
	public Board(Long bId ,String title, String content, String category, String img) {
		this.bId = bId;
		this.title = title;
		this.content = content;
		this.category = category;
		this.img = img;
	}
	
	
	
}
