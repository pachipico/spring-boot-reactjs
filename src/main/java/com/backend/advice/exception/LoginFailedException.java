package com.backend.advice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailedException extends RuntimeException {
	
	public LoginFailedException() {
		super();
	}
	public LoginFailedException(String msg) {
		super(msg);
	}
	public LoginFailedException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
