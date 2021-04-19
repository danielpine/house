package com.house;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.house.pojo.HouseList;
import com.house.repository.HouseRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HouseRepositoryTests {

	@Autowired
	private HouseRepository houseRepository;

	@Test
	public void testHouseRepository() {
		List<HouseList> search = houseRepository.search("上海");
		System.out.println(search);
	}

}
