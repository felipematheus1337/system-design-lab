package system_design_user.v1.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system_design_user.v1.application.dto.GenerateUserReportOutput;
import system_design_user.v1.application.usecases.GenerateUserReportUseCase;

@RestController
@RequestMapping("/users")
public class UserReportController {

    private final GenerateUserReportUseCase useCase;

    public UserReportController(GenerateUserReportUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{userId}/reports")
    public ResponseEntity<GenerateUserReportOutput> generateReport(@PathVariable Long userId) {
        return ResponseEntity.ok(useCase.execute(userId));
    }
}
