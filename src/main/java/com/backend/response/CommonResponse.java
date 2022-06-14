package com.backend.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public enum CommonResponse {

	SUCCESS(0, "성공"), FAIL(-1, "실패");
	private int code;
	private String msg;

}
