package com.example.demo.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args); // Spring 앱 실행을 위함

		// context 를 가져옵니다.
		ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
		ApplicationContext context = applicationContextProvider.getContext();

		String url = "www.naver.com/book/it?size=20&page=10&name=spring";
		// 컴포넌트 스캔으로 자동 등록되어있을 bean 불러오기
		Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
		UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

		//-------------------- [컴포넌트 등록 없는 수동 주입] ------------------------

		// base64 bean을 Encoder 클래스의 생성자를 통해 주입
		Encoder encoder = new Encoder(base64Encoder);
		System.out.println(encoder.encode(url));

		// url bean을 setter로 주입
		encoder.setEncoderInterface(urlEncoder);
		System.out.println(encoder.encode(url));


		//---------------------- [컴포넌트 등록한 자동 주입] ----------------------

		// Encoder 생성자에 Qualifier 를 사용했을 것이니,
		// 주입할 필요없고 bean 으로 불러와서 바로 사용해도 됨
		Encoder encoder1 = context.getBean(Encoder.class);
		System.out.println(encoder1.encode(url));


		//---------------------- [@Bean 지정한 @Configuration 사용] ----------------------

		// 이번엔 AppConfig 클래스에서 등록된 bean 생성자가 호출이 된다.
		MultiEncoder multiEncoder = context.getBean("multi_url", MultiEncoder.class);
		System.out.println(multiEncoder.encode(url));
	}

}

// @Configuration 은 한 개의 클래스에서 여러 개의 @Bean 을 사용할 수 있다.
// @Component 에서 쓰는 @Qualifier 하고는 조금 다른 느낌 같다.

@Configuration
class AppConfig{

	// base64와 url은 이미 컴포넌트 등록이 되어서 자동 주입될 것이다.

	// 지정된 Bean 이름으로 생성자 호출할 수 있다.
	@Bean("multi_base64")
	public MultiEncoder encoder(Base64Encoder base64Encoder){
		return new MultiEncoder(base64Encoder);
	}

	@Bean("multi_url")
	public MultiEncoder encoder(UrlEncoder urlEncoder){
		return new MultiEncoder(urlEncoder);
	}
}