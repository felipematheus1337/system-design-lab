package delivery_api.v1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class V1Application {

	public static void main(String[] args) {
		SpringApplication.run(V1Application.class, args);
	}


}
