package com.backend.user.dto;

import com.backend.user.domain.User;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
public class UserLoginResponseDto {
	private String email;
	private String name;
	private String nickName;
	private String profileImg;
	private String address;
	private int point;
	private int popularity;
	private String regAt;
	private String lastLoggedIn;
	private String siName;
	
	public UserLoginResponseDto(User member) {
		this.email = member.getEmail();
		this.name = member.getName();
		this.nickName = member.getNickName();
		this.profileImg = member.getProfileImg();
		this.address = member.getAddress();
		this.point = member.getPoint();
		this.popularity = member.getPopularity();
		this.regAt = member.getRegAt();
		this.lastLoggedIn = member.getLastLoggedIn();
		this.siName = member.getSiName();
	}
}
