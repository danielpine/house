package com.house.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.pojo.Order;
import com.house.pojo.User;
import com.house.repository.OrderRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public Result apply(@RequestBody Order order, HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		System.out.println(u);
		int code = orderRepository.insert(order);
		if (code == 1) {
			return new Result(true, StatusCode.SUCCESS, "预定成功，等待审核。", order);
		} else {
			return new Result(true, StatusCode.ERROR, "预定失败，请稍后再试。");
		}
	}

}
