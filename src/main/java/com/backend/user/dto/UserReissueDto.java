package com.backend.user.dto;

import com.backend.user.domain.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
public class UserReissueDto {
	private String email;
	private String name;
	private String nickName;
	private String profileImg;
	private String siName;
	private int popularity;
	private int point;

	private String token;
	
	public UserReissueDto(User user, String token) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.nickName = user.getNickName();
		this.profileImg = user.getProfileImg();
		this.token = token;
		this.point = user.getPoint();
		this.siName = user.getSiName();
		this.popularity = user.getPopularity();
	}
}
