package com.backend.advice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CAccessDeniedException extends RuntimeException {
	public CAccessDeniedException() {
		super();
	}
	public CAccessDeniedException(String msg) {
		super(msg);
	}
	public CAccessDeniedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
