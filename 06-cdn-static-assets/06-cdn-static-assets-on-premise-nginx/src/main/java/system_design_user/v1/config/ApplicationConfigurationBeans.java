package system_design_user.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import system_design_user.v1.application.gateway.CreateUserGateway;
import system_design_user.v1.application.gateway.GetUserGateway;
import system_design_user.v1.application.gateway.UserReportFileGateway;
import system_design_user.v1.application.usecases.CreateUserUseCase;
import system_design_user.v1.application.usecases.GenerateUserReportUseCase;
import system_design_user.v1.application.usecases.GetUserByIdUseCase;

@Configuration
public class ApplicationConfigurationBeans {

  @Bean
  public CreateUserUseCase createUserUseCase(CreateUserGateway createUserGateway) {
     return new CreateUserUseCase(createUserGateway);
  }

  @Bean
  public GetUserByIdUseCase getUserByIdUseCase(GetUserGateway gateway) {
      return new GetUserByIdUseCase(gateway);
  }

  @Bean
  public GenerateUserReportUseCase generateUserReportUseCase(GetUserByIdUseCase getUserByIdUseCase,
                                                             UserReportFileGateway fileGateway) {
      return new GenerateUserReportUseCase(getUserByIdUseCase, fileGateway);
  }


}
