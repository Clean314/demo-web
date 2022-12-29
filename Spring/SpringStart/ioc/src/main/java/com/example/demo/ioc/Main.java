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

		Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
		UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
		Encoder encoder = new Encoder(base64Encoder);

		String url = "www.naver.com/book/it?size=20&page=10&name=spring";

		System.out.println(encoder.encode(url));

		// bean 주입
		encoder.setEncoderInterface(urlEncoder);
		System.out.println(encoder.encode(url));
	}

}
