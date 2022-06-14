package com.backend.user.dto;

import com.backend.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
	private String email;
	private String name;
	private String nickName;
	private String password;
	private String address;
	private String siName;

	public User toEntity() {
		return new User(email, password, name, nickName, address, siName);
	}
}
