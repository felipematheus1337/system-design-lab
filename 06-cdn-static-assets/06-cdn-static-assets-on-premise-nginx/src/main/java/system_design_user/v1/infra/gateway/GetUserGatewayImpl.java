package system_design_user.v1.infra.gateway;

import org.springframework.stereotype.Component;
import system_design_user.v1.application.gateway.GetUserGateway;
import system_design_user.v1.domain.User;
import system_design_user.v1.infra.mapper.UserMapper;
import system_design_user.v1.infra.persistence.entities.UserEntity;
import system_design_user.v1.infra.persistence.repository.UserRepository;

@Component
public class GetUserGatewayImpl implements GetUserGateway  {

    private final UserRepository repository;
    private final UserMapper mapper;

    public GetUserGatewayImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User execute(Long id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with that id: " + id));

        return mapper.toDomain(entity);

    }
}
