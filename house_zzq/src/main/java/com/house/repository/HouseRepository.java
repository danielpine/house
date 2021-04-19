package com.house.repository;

import com.house.pojo.HouseList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository {
	@Select("select houseId,address,price,status,detail,city from houselist where city = #{city}")
	List<HouseList> search(@Param("city") String city);
}
