package system_design_user.v1.application.usecases;

import system_design_user.v1.application.dto.GenerateUserReportOutput;
import system_design_user.v1.application.gateway.UserReportFileGateway;
import system_design_user.v1.domain.User;

public class GenerateUserReportUseCase {

    private final GetUserByIdUseCase useCase;
    private final UserReportFileGateway reportFileGateway;

    public GenerateUserReportUseCase(GetUserByIdUseCase useCase, UserReportFileGateway reportFileGateway) {
        this.useCase = useCase;
        this.reportFileGateway = reportFileGateway;
    }

    public GenerateUserReportOutput execute(Long id) {
        User user = useCase.execute(id);

        if (user == null) throw new IllegalArgumentException("User not found. id=" + id);

        return reportFileGateway.generate(user);


    }

}
