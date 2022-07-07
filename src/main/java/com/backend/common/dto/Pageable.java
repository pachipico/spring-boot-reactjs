package com.backend.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class Pageable {
	private int page;
	private int size;
	private int start;

	public Pageable(int page, int size) {
		this.page = page;
		this.size = size;
		this.start = (page - 1) * size;
	}

}
