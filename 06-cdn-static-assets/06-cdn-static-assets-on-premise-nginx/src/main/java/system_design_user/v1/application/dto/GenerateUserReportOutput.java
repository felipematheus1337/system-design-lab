package system_design_user.v1.application.dto;

import java.time.LocalDateTime;

public record GenerateUserReportOutput(Long userId,
                                       String fileName,
                                       String assetPath,
                                       String assetUrl,
                                       LocalDateTime generatedAt) {
}
