package com.backend.user.service;

import java.util.List;

import com.backend.user.domain.User;
import com.backend.user.dto.UserDetailsDto;
import com.backend.user.dto.UserLoginRequestDto;
import com.backend.user.dto.UserReissueDto;
import com.backend.user.dto.UserModifyRequestDto;
import com.backend.user.dto.UserResponseDto;
import com.backend.user.dto.UserSignupRequestDto;


public interface UserService {
	int register(UserSignupRequestDto userSignupRequestDto);
	UserResponseDto findByEmail(String email);
	UserResponseDto findByName(String name);
	UserDetailsDto findUserDetails(String email);
	List<UserResponseDto> findByNickName(String nickName);
	List<UserResponseDto> findByLocation(String siName);
	List<UserResponseDto> findAll();
	int updateUserInfo(UserModifyRequestDto userModifyRequestDto, String category);
	
	int deleteUser(String email);
	List<Long> getUserLikedBoardBId(String email);
}
