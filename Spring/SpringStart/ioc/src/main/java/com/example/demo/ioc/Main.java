package com.example.demo.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
		ApplicationContext context = applicationContextProvider.getContext();

		String url = "www.naver.com/book/it?size=20&page=10&name=spring";
		// 등록된 bean 불러오기
		Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
		UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
		
		// base64 bean을 Encoder 클래스의 생성자를 통해 주입
		Encoder encoder = new Encoder(base64Encoder);
		System.out.println(encoder.encode(url));

		// url bean을 setter로 주입
		encoder.setEncoderInterface(urlEncoder);
		System.out.println(encoder.encode(url));

		//--------------------------------------------

		// Qualifier 를 사용했다면 주입할 필요가 없고 bean 으로 불러와서 바로 사용
		Encoder encoder1 = context.getBean(Encoder.class);
		System.out.println(encoder1.encode(url));
	}

}
