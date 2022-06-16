package com.backend.security.service;

import java.util.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.security.jwt.JwtProvider;
import com.backend.user.domain.User;
import com.backend.user.dto.UserLoginRequestDto;
import com.backend.user.dto.UserReissueDto;
import com.backend.user.dto.UserSignupRequestDto;
import com.backend.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {
	private final UserMapper userMapper;
	private final PasswordEncoder encoder;
	private final JwtProvider jwtProvider;

	@Transactional
	public String login(UserLoginRequestDto userLoginRequestDto) {
		User user = userMapper.findByEmail(userLoginRequestDto.getEmail());
		String token = null;
		if (encoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
			token = jwtProvider.createToken(userLoginRequestDto.getEmail(), Arrays.asList(user.getRoles().split(",")));
		}
		userMapper.updateUserLastLoggedIn(userLoginRequestDto.getEmail());
		return token;
	}

	@Transactional
	public int signup(UserSignupRequestDto userSignupRequestDto) {
		if(userMapper.findByEmail(userSignupRequestDto.getEmail()) != null) return -2;
		if(userMapper.isNickNameAvailable(userSignupRequestDto.getNickName()) != null) return -1; 
		userSignupRequestDto.setPassword(encoder.encode(userSignupRequestDto.getPassword()));
		return userMapper.register(userSignupRequestDto.toEntity());
		
	}
	
	@Transactional
	public UserReissueDto reissue(String token) {
		UserReissueDto userReissueDto = null;
		if(jwtProvider.validateToken(token)) {
			Authentication authentication = jwtProvider.getAuthentication(token);
			userReissueDto = new UserReissueDto(userMapper.findByEmail(authentication.getName()), token);
		}
		return userReissueDto;
	}
	
}
