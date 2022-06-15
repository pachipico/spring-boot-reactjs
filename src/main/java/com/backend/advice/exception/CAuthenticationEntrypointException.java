package com.backend.advice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CAuthenticationEntrypointException extends RuntimeException {
	public CAuthenticationEntrypointException() {
		super();
	}
	public CAuthenticationEntrypointException(String msg) {
		super(msg);
	}
	public CAuthenticationEntrypointException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
