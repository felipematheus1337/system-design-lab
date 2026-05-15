package system_design_user.v1.application.usecases;

import system_design_user.v1.application.gateway.CreateUserGateway;
import system_design_user.v1.domain.User;

public class CreateUserUseCase {

    private final CreateUserGateway gateway;

    public CreateUserUseCase(CreateUserGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(User user) {
        gateway.create(user);
    }

}
