package com.backend.advice.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundCException extends RuntimeException {
	public UserNotFoundCException() {
		super();
	}
}
