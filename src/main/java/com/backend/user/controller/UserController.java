package com.backend.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.response.ResponseService;
import com.backend.response.result.CommonResult;
import com.backend.response.result.ListResult;
import com.backend.response.result.SingleResult;
import com.backend.user.dto.UserLoginRequestDto;
import com.backend.user.dto.UserLoginResponseDto;
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
	
	@PostMapping("/user/signup")
	public CommonResult signup (@RequestBody UserSignupRequestDto userSignupRequestDto) {
		return userService.register(userSignupRequestDto) > 0 ? responseService.getSuccessfulResult() : responseService.getFailResult();
	}
	
	@GetMapping("/users")
	public ListResult<UserResponseDto> users(){
		return responseService.getListResult(userService.findAll());
	}
	
	@GetMapping("/user/email/{email}")
	public SingleResult<UserResponseDto> findByEmail(@PathVariable("email") String email){
		return responseService.getSingleResult(userService.findByEmail(email));
	}
	
	@GetMapping("/user/name/{name}")
	public SingleResult<UserResponseDto> findByName(@PathVariable("name") String name){
		return responseService.getSingleResult(userService.findByName(name));
	}
	
	@GetMapping("/user/nickName/{nickName}")
	public ListResult<UserResponseDto> findByNickName(@PathVariable("nickName") String nickName){
		return responseService.getListResult(userService.findByNickName(nickName));
	}
	
	@GetMapping("/user/location/{siName}")
	public ListResult<UserResponseDto> findByLocation(@PathVariable("siName") String siName){
		return responseService.getListResult(userService.findByLocation(siName));
	}
	
	@PutMapping("/user/modify/{category}")
	public SingleResult<Integer> modify(@RequestBody UserModifyRequestDto userModifyRequestDto, @PathVariable("category") String category){
		return responseService.getSingleResult(userService.updateUser(userModifyRequestDto, category));
	}
	
	@DeleteMapping("/user/delete/{email}")
	public CommonResult delete(@PathVariable("email") String email) {
		return userService.deleteUser(email) > 0 ? responseService.getSuccessfulResult() : responseService.getFailResult();
	}
}
