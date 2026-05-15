package system_design_user.v1.application.gateway;

import system_design_user.v1.application.dto.GenerateUserReportOutput;
import system_design_user.v1.domain.User;

public interface UserReportFileGateway {

    GenerateUserReportOutput generate(User user);
}
