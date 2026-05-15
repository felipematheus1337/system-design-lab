package system_design_user.v1.infra.gateway;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import system_design_user.v1.application.gateway.CreateUserGateway;
import system_design_user.v1.domain.User;
import system_design_user.v1.infra.mapper.UserMapper;
import system_design_user.v1.infra.persistence.entities.UserEntity;
import system_design_user.v1.infra.persistence.repository.UserRepository;

@Component
public class CreateUserGatewayImpl implements CreateUserGateway {

    private final UserRepository repository;
    private final UserMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(CreateUserGatewayImpl.class);

    public CreateUserGatewayImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void create(User user) {

        log.info("::: CREATING AN USER :::");

        UserEntity entity = mapper.toEntity(user);
        repository.save(entity);

    }
}
