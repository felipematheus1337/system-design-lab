package system_design_user.v1.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system_design_user.v1.application.usecases.CreateUserUseCase;
import system_design_user.v1.domain.User;
import system_design_user.v1.infra.controller.request.UserRequest;
import system_design_user.v1.infra.mapper.UserMapper;

@RestController
@RequestMapping("/api/v1/user")
public class BusinessController {

    private final CreateUserUseCase createUserUseCase;
    private final UserMapper mapper;

    public BusinessController(CreateUserUseCase createUserUseCase, UserMapper mapper) {
        this.createUserUseCase = createUserUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody UserRequest request) {
        User domain  = mapper.requestToDomain(request);
        createUserUseCase.execute(domain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
