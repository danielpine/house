package com.house.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dao.PaidDao;
import com.house.pojo.HouseList;
import com.house.pojo.Order;
import com.house.pojo.Paid;
import com.house.pojo.User;
import com.house.repository.OrderRepository;
import com.house.service.HouseListService;
import com.house.service.PaidService;
import com.house.utils.Asserts;

@RestController
@CrossOrigin
@RequestMapping(value = "/order")
@Transactional
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private HouseListService houseListService;
	@Autowired
	private PaidDao paidDao;
	@Autowired
	private PaidService paidService;

	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public Result apply(@RequestBody Order order, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Asserts.checkUser(user);
		Assert.isTrue(user.getType() == 2, "仅租客可以预定房屋");
		HouseList house = houseListService.findHouseById(order.getHouseid());
		Assert.isTrue(StringUtils.equals("未出租", house.getStatus()), "晚了一步，房屋已被预订！");
		Assert.notNull(house, "房屋不存在");
		house.setStatus("已出租");
		house.setUserlist_Id(user.getId());
		house.setUserlist_Name(user.getUserList().getName());
		houseListService.updateHouse(house);
		order.setUserid(user.getId());
		order.setStatus("待支付");
		order.setCreatedate(new Date());
		int code = orderRepository.insert(order);
		if (code == 1) {
			// 押金
			Paid ya = new Paid();
			ya.setPaytype("押金");
			ya.setPrice(house.getPrice());
			ya.setAddress(house.getAddress());
			ya.setStatus("未缴");
			ya.setHouseId(house.getHouseId());
			ya.setUserlist_id(user.getId());
			ya.setOrderid(order.getOrderid());
			ya.setName(user.getUserList().getName());
			ya.setDate(order.getStartdate());
			ya.setPayDate(new Date());
			paidDao.insertPaid(ya);
			// 房租
			Paid zu = new Paid();
			zu.setPaytype("租金");
			zu.setPrice(house.getPrice() * order.getMonths());
			zu.setAddress(house.getAddress());
			zu.setStatus("未缴");
			zu.setHouseId(house.getHouseId());
			zu.setUserlist_id(user.getId());
			zu.setOrderid(order.getOrderid());
			zu.setName(user.getUserList().getName());
			zu.setDate(order.getStartdate());
			zu.setPayDate(new Date());
			paidDao.insertPaid(zu);
			return new Result(true, StatusCode.SUCCESS, "预定成功，请支付。", house);
		} else {
			return new Result(true, StatusCode.ERROR, "预定失败，请稍后再试。");
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result list(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Asserts.checkUser(user);
		List<Order> orders = orderRepository.listUserOrder(user.getId());
		for (Order order : orders) {
			order.setHouse(houseListService.findHouseById(order.getHouseid()));
			order.setPaids(paidDao.listByOrderId(order.getOrderid()));
		}
		return new Result(true, StatusCode.SUCCESS, "查询成功。", orders);
	}

	@RequestMapping(value = "/paysuccess", method = RequestMethod.GET)
	public Result paysuccess(HttpServletRequest request, @RequestParam("orderid") String orderid) {
		User user = (User) request.getSession().getAttribute("user");
		Asserts.checkUser(user);
		paidService.listByOrderId(orderid).forEach(this::updateToPaidStatus);
		orderRepository.updateUserOrderStatus(orderid, "生效中");
		return new Result(true, StatusCode.SUCCESS, "支付成功！", null);
	}

	@RequestMapping(value = "/surrender", method = RequestMethod.GET)
	public Result surrender(HttpServletRequest request, @RequestParam("orderid") String orderid) {
		User user = (User) request.getSession().getAttribute("user");
		Asserts.checkUser(user);
		orderRepository.updateUserOrderStatus(orderid, "已完成");
		Order order = orderRepository.selectUserOrder(orderid);
		HouseList house = houseListService.findHouseById(order.getHouseid());
		house.setStatus("未出租");
		house.setUserlist_Id(null);
		house.setUserlist_Name(null);
		houseListService.updateHouse(house);
		return new Result(true, StatusCode.SUCCESS, "退租成功！", null);
	}

	private void updateToPaidStatus(Paid paid) {
		paid.setStatus("已缴");
		paidService.updatePaid(paid);
	}

}
