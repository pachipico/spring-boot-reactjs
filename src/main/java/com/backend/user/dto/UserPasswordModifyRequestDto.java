package com.backend.user.dto;

import com.backend.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserPasswordModifyRequestDto extends UserModifyRequestDto{
	private String newPassword;
	@Override
		public User toEntity() {
			
			return new User(getEmail(), newPassword, getNickName(), getProfileImg(), getPoint(), getPopularity(), getAddress(), getLastLoggedIn(), getSiName());
		}
}
