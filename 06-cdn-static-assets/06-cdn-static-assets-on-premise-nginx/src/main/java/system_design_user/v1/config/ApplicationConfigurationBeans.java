package system_design_user.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import system_design_user.v1.application.gateway.CreateUserGateway;
import system_design_user.v1.application.usecases.CreateUserUseCase;

@Configuration
public class ApplicationConfigurationBeans {

  @Bean
  public CreateUserUseCase createUserUseCase(CreateUserGateway createUserGateway) {
     return new CreateUserUseCase(createUserGateway);
  }


}
