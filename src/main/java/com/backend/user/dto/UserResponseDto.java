package com.backend.user.dto;

import com.backend.user.domain.User;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResponseDto {
	private String email;
	private String name;
	private String nickName;
	private String profileImg;
	private String address;
	private int point;
	private int popularity;
	private String regAt;
	
	public UserResponseDto(User user) {
		
		this.email = user.getEmail();
		this.name = user.getName();
		this.nickName = user.getNickName();
		this.profileImg = user.getProfileImg();
		this.address = user.getAddress();
		this.point = user.getPoint();
		this.popularity = user.getPopularity();
		this.regAt = user.getRegAt();
	}
}
