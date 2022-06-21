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
@AllArgsConstructor
public class BoardQuery {

//	String field, String query, String siName, String category, String orderBy, int start
	private String field;
	private String query;
	private String siName;
	private String category;
	private String orderBy;
	private int start;
	private int cnt;
}
