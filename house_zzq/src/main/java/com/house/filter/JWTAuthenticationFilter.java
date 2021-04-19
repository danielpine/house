package com.house.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import com.house.service.TokenAuthenticationService;

public class JWTAuthenticationFilter extends GenericFilterBean {

	private final static List<String> noAuthPaths = Arrays.asList(
	        "/error",
	        "/house/**",
	        "/user/login");

	boolean isAuthness(String path) {
		return noAuthPaths.stream().anyMatch(pattern -> new AntPathMatcher().match(pattern, path));
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	        throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		System.out.println("JWTAuthenticationFilter.doFilter");
		System.out.println(req.getMethod());
		System.out.println(req.getServletPath());
		if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else if (isAuthness(req.getServletPath())) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			Authentication authentication = TokenAuthenticationService
			        .getAuthentication(req);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}
}
