package com.house.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.pojo.HouseList;
import com.house.pojo.Order;
import com.house.pojo.User;
import com.house.repository.OrderRepository;
import com.house.service.HouseListService;

@RestController
@CrossOrigin
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private HouseListService houseListService;

	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public Result apply(@RequestBody Order order, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Assert.notNull(user, "登录已失效，请注销然后重新登录！");
		HouseList house = houseListService.findHouseById(order.getHouseid());
		Assert.isTrue(StringUtils.equals("未出租", house.getStatus()), "晚了一步，房屋已被预订！");
		Assert.notNull(house, "房屋不存在");
		house.setStatus("已出租");
		house.setUserlist_Id(user.getId());
		house.setUserlist_Name(user.getUsername());
		houseListService.updateHouse(house);
		order.setUserid(user.getId());
		order.setStatus("待审核");
		order.setCreatedate(new Date());
		int code = orderRepository.insert(order);
		if (code == 1) {
			return new Result(true, StatusCode.SUCCESS, "预定成功，等待审核。", house);
		} else {
			return new Result(true, StatusCode.ERROR, "预定失败，请稍后再试。");
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result list(@RequestBody Order order, HttpServletRequest request) { 
		User user = (User) request.getSession().getAttribute("user");
		Assert.notNull(user, "登录已失效，请注销然后重新登录！");
		List<Order> userOrders = orderRepository.listUserOrder(user.getId());
		return new Result(true, StatusCode.SUCCESS, "查询成功。", userOrders);
	}

}
