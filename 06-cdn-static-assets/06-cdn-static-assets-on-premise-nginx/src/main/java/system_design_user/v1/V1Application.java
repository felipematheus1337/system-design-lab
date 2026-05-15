package system_design_user.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import system_design_user.v1.config.AssetsProperties;

@SpringBootApplication
@EnableConfigurationProperties(AssetsProperties.class)
public class V1Application {

	public static void main(String[] args) {
		SpringApplication.run(V1Application.class, args);
	}

}
