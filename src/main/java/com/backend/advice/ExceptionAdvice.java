package com.backend.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.advice.exception.LoginFailedException;
import com.backend.advice.exception.CAccessDeniedException;
import com.backend.advice.exception.CAuthenticationEntrypointException;
import com.backend.advice.exception.UserNotFoundCException;
import com.backend.response.ResponseService;
import com.backend.response.result.CommonResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	
	private final ResponseService responseService;
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult defaultException(HttpServletRequest request, Exception e) {
		return responseService.getFailResult();
	}
	
	@ExceptionHandler(UserNotFoundCException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, UserNotFoundCException e) {
		return responseService.getFailResult();
	}
	
	@ExceptionHandler(CAuthenticationEntrypointException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	protected CommonResult notAuthorizedException(HttpServletRequest request, CAuthenticationEntrypointException e) {
		return responseService.getFailResult(-1, "권한이 없습니다");
	}
	
	@ExceptionHandler(LoginFailedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult loginFailedException(HttpServletRequest request, LoginFailedException e) {
		return responseService.getFailResult(-1, "로그인 실패");
	}
	
	@ExceptionHandler(CAccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	protected CommonResult accessDeniedException(HttpServletRequest request, CAccessDeniedException e) {
		return responseService.getFailResult(-1, "접근 권한이 없습니다.");
	}
}
