package com.house.utils;

import java.util.Objects;

import com.house.exception.TokenExpirationException;
import com.house.pojo.User;

public final class Asserts {

	private Asserts() {
	}

	public static void checkUser(User user) {
		if (Objects.isNull(user)) {
			throw new TokenExpirationException("登录已失效，请注销然后重新登录！");
		}
	}
}
