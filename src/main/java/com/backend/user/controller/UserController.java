package com.backend.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.advice.exception.UserNotFoundCException;
import com.backend.response.ResponseService;
import com.backend.response.result.CommonResult;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;
import com.backend.security.service.SecurityService;
import com.backend.user.dto.UserLoginRequestDto;
import com.backend.user.dto.UserModifyRequestDto;
import com.backend.user.dto.UserResponseDto;
import com.backend.user.dto.UserSignupRequestDto;
import com.backend.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
	private final UserService userService;
	private final ResponseService responseService;
	private final SecurityService securityService;
	
	@PostMapping("/signup")
	public CommonResult signup (@RequestBody UserSignupRequestDto userSignupRequestDto) {
		return securityService.signup(userSignupRequestDto) > 0 ? responseService.getSuccessfulResult() : responseService.getFailResult();
	}
	
	@PostMapping("/login")
	public CommonResult login(@RequestBody UserLoginRequestDto userLoginRequestDto){
		String token = securityService.login(userLoginRequestDto);
		return token == null ? responseService.getFailResult() : responseService.getSingleResult(token);
	}
	
	@GetMapping("/users")
	public ListResult<UserResponseDto> users() {
		List<UserResponseDto> users = userService.findAll();
		if(users.size() == 0 || users == null) throw new UserNotFoundCException();
		else return responseService.getListResult(userService.findAll());
	}
	
	@GetMapping("/user/email/{email}")
	public SingleResult<UserResponseDto> findByEmail(@PathVariable("email") String email){
		UserResponseDto user = userService.findByEmail(email);
		if(user == null) throw new UserNotFoundCException();
		else return responseService.getSingleResult(user);
	}
	
	@GetMapping("/user/name/{name}")
	public SingleResult<UserResponseDto> findByName(@PathVariable("name") String name){
		UserResponseDto user = userService.findByName(name);
		if(user == null) throw new UserNotFoundCException();
		else return responseService.getSingleResult(user);
	}
	
	@GetMapping("/user/nickname/{nickName}")
	public ListResult<UserResponseDto> findByNickName(@PathVariable("nickName") String nickName){
		List<UserResponseDto> users = userService.findByNickName(nickName);
		if(users.size() == 0 || users == null) throw new UserNotFoundCException();
		else return responseService.getListResult(users);
	}
	
	@GetMapping("/user/location/{siName}")
	public ListResult<UserResponseDto> findByLocation(@PathVariable("siName") String siName){
		List<UserResponseDto> users = userService.findByLocation(siName);
		if(users.size() == 0 || users == null) throw new UserNotFoundCException();
		else return responseService.getListResult(users);
	}
	
	@PutMapping("/user/modify/{category}")
	public CommonResult modify(@RequestBody UserModifyRequestDto userModifyRequestDto, @PathVariable("category") String category){
		int res = userService.updateUserInfo(userModifyRequestDto, category);
		return res > 0 ? responseService.getSuccessfulResult() : responseService.getFailResult();
	}
	
	@DeleteMapping("/user/delete/{email}")
	public CommonResult delete(@PathVariable("email") String email) {
		return userService.deleteUser(email) > 0 ? responseService.getSuccessfulResult() : responseService.getFailResult();
	}
}
