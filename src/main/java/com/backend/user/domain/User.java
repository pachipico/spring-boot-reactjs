package com.backend.user.domain;

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
public class User {
	private String email;
	private String password;
	private String name;
	private String nickName;
	private String profileImg;
	private int point;
	private int popularity;
	private String regAt;
	private String address;
	private String lastLoggedIn;
	private String siName;

	/**
	 * 로그인 시도 dto에 사용될 생성자.
	 * @param email
	 * @param password
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	
	/**
	 * 회원가입 dto에 사용될 생성자.
	 * @param email
	 * @param password
	 * @param name
	 * @param nickName
	 * @param address
	 */
	public User(String email, String password, String name, String nickName, String address, String siName) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.address = address;
		this.siName = siName;
	}

	
	
	
}
