package com.backend.response.result;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class CommonResult {
	private boolean success;
	private int code;
	private String msg;
}
