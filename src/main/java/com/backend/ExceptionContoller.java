package com.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.advice.exception.CAccessDeniedException;
import com.backend.advice.exception.CAuthenticationEntrypointException;
import com.backend.response.result.CommonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionContoller {

	@GetMapping("/entrypoint")
	public CommonResult entryPointException() {
		throw new CAuthenticationEntrypointException();
	}
	
	@GetMapping("/accessDenied")
	public CommonResult accessDeniedException() {
		throw new CAccessDeniedException();
	}
}
