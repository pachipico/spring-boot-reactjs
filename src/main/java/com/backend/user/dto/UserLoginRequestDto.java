package com.backend.user.dto;

import com.backend.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDto {
	private String email;
	private String password;
	
	public User toEntity () {
		return new User(email, password);
	}
}
