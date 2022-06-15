package com.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.backend.advice.exception.CAuthenticationEntrypointException;
import com.backend.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
	
	private final JwtProvider jwtProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = jwtProvider.resolveToken((HttpServletRequest) request);
		log.debug("인증 처리 필터");
		log.debug(">>>>>>>>>Token>>>>>>>>>:{}",token );
//		if(token == null || token.trim() == "") throw new NotAuthorizedException();
		if(token != null && jwtProvider.validateToken(token)) {
			log.debug("????");
			Authentication authentication = jwtProvider.getAuthentication(token);
			log.debug("???????????????? {}", authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

}
