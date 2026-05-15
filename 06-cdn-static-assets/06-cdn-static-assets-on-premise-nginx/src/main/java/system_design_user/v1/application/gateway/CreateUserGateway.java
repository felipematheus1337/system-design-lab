package system_design_user.v1.application.gateway;

import system_design_user.v1.domain.User;

public interface CreateUserGateway {

    void create(User user);
}
