package system_design_user.v1.infra.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import system_design_user.v1.domain.User;
import system_design_user.v1.infra.controller.request.UserRequest;
import system_design_user.v1.infra.persistence.entities.UserEntity;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserEntity toEntity(User user);

  User toDomain(UserEntity entity);

  User requestToDomain(UserRequest request);
}
