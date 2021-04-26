package com.house.handler;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.exception.TokenExpirationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by treasure.zhou on 2019/3/22.
 */
@RestControllerAdvice
public class BaseExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public Result exception(Exception e) {
		e.printStackTrace();
		return new Result(false, StatusCode.ERROR, e.getMessage());
	}

	@ExceptionHandler(value = TokenExpirationException.class)
	public  ResponseEntity<Object>  tokenExpirationException(TokenExpirationException e) {
		e.printStackTrace();
		return  new ResponseEntity<Object>(e.getMessage(),  HttpStatus.UNAUTHORIZED);
	}
}
