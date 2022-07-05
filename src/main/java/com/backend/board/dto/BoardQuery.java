package com.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class BoardQuery {

//	String field, String query, String siName, String category, String orderBy, int start
	private String field;
	private String query;
	private String siName;
	private String category;
	private String orderBy;
	private int page;
	private int size;
	private int start;
	public BoardQuery(String field, String query, String siName, String category, String orderBy, int page, int size) {
		this.field = field;
		this.query = query;
		this.siName = siName;
		this.category = category;
		this.orderBy = orderBy;
		this.page = page;
		this.size = size;
		this.start = (page - 1) * size;
	}
	
}
