package com.backend.user.dto;

import com.backend.user.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserModifyRequestDto {
	private String email;
	private String nickName;
	private String address;
	private String siName;
	private String profileImg;
	private String password;

	public User toEntity () {
		return new User(email, password, null, nickName, address, siName);
	}

}
