package com.backend.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.user.domain.User;
import com.backend.user.dto.UserLoginRequestDto;
import com.backend.user.dto.UserLoginResponseDto;
import com.backend.user.dto.UserModifyRequestDto;
import com.backend.user.dto.UserResponseDto;
import com.backend.user.dto.UserSignupRequestDto;
import com.backend.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	// 시큐리티 적용 후 비밀번호 인코딩/디코딩
	// private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public int register(UserSignupRequestDto userSignupRequestDto) {

		return userMapper.register(userSignupRequestDto.toEntity());
	}

	@Override
	@Transactional
	public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
		// 시큐리티 적용 후 비밀번호 인코딩/디코딩
		if (userMapper.findByEmail(userLoginRequestDto.getEmail()) == null) {
			throw new RuntimeException("해당 이메일의 유저가 존재하지 않습니다.");
		}
		return new UserLoginResponseDto(
				userMapper.login(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword()));

	}

	@Override
	@Transactional
	public UserResponseDto findByEmail(String email) {

		return new UserResponseDto(userMapper.findByEmail(email));
	}

	@Override
	@Transactional
	public UserResponseDto findByName(String name) {

		return new UserResponseDto(userMapper.findByName(name));
	}

	@Override
	@Transactional
	public List<UserResponseDto> findByNickName(String nickName) {

		return userMapper.findByNickName(nickName).stream().map(u -> new UserResponseDto(u))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<UserResponseDto> findByLocation(String siName) {

		return userMapper.findByLocation(siName).stream().map(u -> new UserResponseDto(u)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<UserResponseDto> findAll() {

		return userMapper.findAll().stream().map(u -> new UserResponseDto(u)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public int updateUser(UserModifyRequestDto userModifyRequestDto, String category) {

		switch (category) {
		case "nickName":
			return userMapper.updateUserNickName(userModifyRequestDto.toEntity());
		case "address":
			return userMapper.updateUserAddress(userModifyRequestDto.toEntity());
		case "password":
			return userMapper.updateUserPassword(userModifyRequestDto.toEntity());
		case "profileImg":
			return userMapper.updateUserProfileImg(userModifyRequestDto.toEntity());
		default:
			return 0;
		}
	}

	@Override
	@Transactional
	public int deleteUser(String email) {

		return userMapper.deleteUser(email);
	}

}
