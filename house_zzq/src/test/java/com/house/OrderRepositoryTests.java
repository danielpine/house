package com.house;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.house.pojo.Order;
import com.house.repository.OrderRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTests {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testListOrderByUserId() {
		List<Order> list = orderRepository.listUserOrder(1);
		System.out.println(list);
	}

	@Test
	public void testRepository() {
		Order order = new Order();
		order.setHouseid(1);
		order.setUserid(2);
		order.setStatus("待审核");
		order.setCreatedate(new Date());
		order.setStartdate(new Date());
		order.setEnddate(new Date());
		System.out.println(orderRepository.insert(order));
		System.out.println(order);
	}

}
