package com.house.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.house.pojo.Order;

@Repository
public interface OrderRepository {
	@SelectKey(keyProperty = "orderid", resultType = String.class, before = true, statement = "select replace(uuid(), '-', '')")
	@Options(keyProperty = "orderid", useGeneratedKeys = true)
	@Insert("INSERT INTO orders (orderid,houseid,userid,status,createdate,startdate,enddate,months) "
			+ "VALUES (#{orderid}, #{houseid}, #{userid}, #{status}, #{createdate}, #{startdate}, #{enddate}, #{months})")
	int insert(Order order);

	@Select("SELECT * FROM orders WHERE userid=#{id} order by createdate desc")
	List<Order> listUserOrder(Integer id);

	@Select("SELECT * FROM orders WHERE orderid=#{orderid}")
	Order selectUserOrder(String orderid);

	@Update("UPDATE orders set status=#{status} WHERE orderid=#{orderid}")
	int updateUserOrderStatus(@Param("orderid") String orderid, @Param("status") String status);

}
