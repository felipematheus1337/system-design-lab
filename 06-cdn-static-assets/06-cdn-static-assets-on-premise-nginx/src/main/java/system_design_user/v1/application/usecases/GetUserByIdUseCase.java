package system_design_user.v1.application.usecases;

import system_design_user.v1.application.gateway.GetUserGateway;
import system_design_user.v1.domain.User;

public class GetUserByIdUseCase {

    private final GetUserGateway gateway;

    public GetUserByIdUseCase(GetUserGateway gateway) {
        this.gateway = gateway;
    }

    public User execute(Long id) {
        return gateway.execute(id);
    }
}
