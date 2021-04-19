package com.house.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.alibaba.fastjson.JSONObject;
import com.house.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	static final long _expiretime = 864_000_000;
	static final String _secret = "itcast";
	static final String _token_prefix = "Bearer";
	static final String _header_string = "Authorization";

	public static void addAuthentication(HttpServletResponse res, String username) throws IOException {
		String JWT = Jwts.builder().setSubject(username)
		        .setExpiration(new Date(System.currentTimeMillis() + _expiretime))
		        .signWith(SignatureAlgorithm.HS512, _secret).compact();
		res.addHeader(_header_string, _token_prefix + " " + JWT);
		res.setContentType("application/json");
		res.setStatus(HttpServletResponse.SC_OK);
		res.getOutputStream().println((new JSONObject() {
			{
				put("status", 0);
				put("message", "");
				put("result", JWT);
			}
		}).toString());

	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(_header_string);
		System.out.println("token : " + token);
		if (token != null) {
			// parse the token.
			JwtUtil jwtUtil = new JwtUtil();
			Claims parseJWT = jwtUtil.parseJWT(token);
			parseJWT.getSubject();
			String user = parseJWT.getSubject();
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;
	}
}
