package com.backend.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.backend.user.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

	private final UserDetailsService userDetailsService;

	private Long accessTokenValidMillisecond = 3 * 60 * 60 * 1000L;
	
	@Value("spring.jwt.secret")
	private String secretKey;
	
	@PostConstruct
	public void init() {
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String email, List<String> roles) {
		Date now = new Date();
		User user = (User) userDetailsService.loadUserByUsername(email);
		String token = JWT.create().withSubject(email).withIssuedAt(now)
				.withClaim("name", user.getName())
				.withClaim("nickName", user.getNickName())
				.withClaim("roles", roles).withExpiresAt(new Date(now.getTime() + accessTokenValidMillisecond))
				.sign(Algorithm.HMAC512(secretKey));
		return token;
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userEntity = userDetailsService.loadUserByUsername(JWT.decode(token).getSubject());
		return new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());
	}
	
	public String resolveToken (HttpServletRequest request) {
		String token = request.getHeader("X-AUTH-TOKEN");
		return token;
	}
	
	public boolean validateToken(String token) {
		log.debug("!!!!!!{}!!!!!!", token);
		return (token == null || token == "") ? false : JWT.decode(token).getExpiresAt().after(new Date());
	}
}
