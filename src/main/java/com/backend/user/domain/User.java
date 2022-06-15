package com.backend.user.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails {
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
	private String roles;

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
	
	/**
	 * 유저 정보 수정에 사용될 생성자.
	 * @param email
	 * @param password
	 * @param nickName
	 * @param profileImg
	 * @param point
	 * @param popularity
	 * @param address
	 * @param lastLoggedIn
	 * @param siName
	 */
	public User(String email, String password, String nickName, String profileImg, int point, int popularity,
			String address, String lastLoggedIn, String siName) {
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.profileImg = profileImg;
		this.point = point;
		this.popularity = popularity;
		this.address = address;
		this.lastLoggedIn = lastLoggedIn;
		this.siName = siName;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Arrays.asList(this.roles.split(",")).forEach(r -> authorities.add(() -> r));
		return authorities;
	}

	

	@Override
	public String getUsername() {
		
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}



	@Override
	public boolean isEnabled() {
		
		return true;
	}



	

	
	
	
}
