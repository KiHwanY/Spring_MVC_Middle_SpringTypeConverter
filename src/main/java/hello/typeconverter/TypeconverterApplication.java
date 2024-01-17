package hello.typeconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 문자를 숫자로 변환하거나, 반대로 숫자를 문자로 변환해야 하는 것처럼 애플리케이션을 개발하다 보면 타입을 변환해야 하는 경우가 상당히 많다.
@SpringBootApplication
public class TypeconverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypeconverterApplication.class, args);
	}

}
