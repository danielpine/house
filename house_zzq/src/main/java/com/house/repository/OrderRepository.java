package com.house.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.house.pojo.Order;

@Repository
public interface OrderRepository {
	@SelectKey(keyProperty = "orderid", resultType = String.class, before = true, statement = "select replace(uuid(), '-', '')")
	@Options(keyProperty = "orderid", useGeneratedKeys = true)
	@Insert("INSERT INTO orders (orderid,houseid,userid,status,createdate,startdate,enddate) "
			+ "VALUES (#{orderid}, #{houseid}, #{userid}, #{status}, #{createdate}, #{startdate}, #{enddate})")
	int insert(Order order);
}
