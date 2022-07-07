package com.backend.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class PageableWithEmail extends Pageable {
	private String email;

	public PageableWithEmail(int page, int size) {
		super(page, size);
	}

	public PageableWithEmail(int page, int size, String email) {
		super(page, size);
		this.email = email;
	}

}
