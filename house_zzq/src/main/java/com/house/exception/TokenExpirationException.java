package com.house.exception;

public class TokenExpirationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TokenExpirationException(String msg) {
		super(msg);
	}
}
