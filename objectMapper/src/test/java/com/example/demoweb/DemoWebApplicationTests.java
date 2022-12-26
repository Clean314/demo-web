package com.example.demoweb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoWebApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		System.out.println("------------- [Object Mapper] -------------");
		var objectMapper = new ObjectMapper();

		// object mapper 가 object -> text 변환 시, get 메소드를 참조한다.
		var user = new User("steve", 19, "010-0000-1111");
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);


		// text -> object 변환 시에는 default 생성자를 참조
		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);
	}

}
