package com.backend.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Comment {
	private Long cId;
	private String writer;
	private String nickName;
	private String regAt;
	private String modAt;
	private String content;
	private Long bId;
	
	/**
	 * register에 쓸 생성자.
	 * @param bId
	 * @param writer
	 * @param content
	 */
	public Comment(Long bId, String writer, String content) {
		this.bId = bId;
		this.writer = writer;
		this.content = content;
	}
	
	/**
	 * update 에 쓸 생성
	 * @param cId
	 * @param content
	 */
	public Comment(Long cId, String content) {
		this.cId = cId;
		this.content = content;
	}
	
	
}
