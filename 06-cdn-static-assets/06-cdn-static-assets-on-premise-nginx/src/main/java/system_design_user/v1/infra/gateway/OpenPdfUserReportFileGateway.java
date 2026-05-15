package system_design_user.v1.infra.gateway;

import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import system_design_user.v1.application.dto.GenerateUserReportOutput;
import system_design_user.v1.application.gateway.UserReportFileGateway;
import system_design_user.v1.config.AssetsProperties;
import system_design_user.v1.domain.User;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class OpenPdfUserReportFileGateway implements UserReportFileGateway {

    private static final String REPORTS_FOLDER = "reports";
    private final AssetsProperties assetsProperties;

    public OpenPdfUserReportFileGateway(AssetsProperties assetsProperties) {
        this.assetsProperties = assetsProperties;
    }

    @Override
    public GenerateUserReportOutput generate(User user) {
        try {
            Path reportsPath = Path.of(assetsProperties.basePath(), REPORTS_FOLDER);
            Files.createDirectories(reportsPath);

            String fileName = buildFileName(user.getId());
            Path reportFilePath = reportsPath.resolve(fileName);

            try (OutputStream outputStream = Files.newOutputStream(reportFilePath)) {
                writePdf(user, outputStream);
            }

            String assetPath = "/reports/" + fileName;
            String assetUrl = assetsProperties.publicBaseUrl() + assetPath;

            return new GenerateUserReportOutput(
                    user.getId(),
                    fileName,
                    assetPath,
                    assetUrl,
                    LocalDateTime.now()
            );
        } catch (Exception exception) {
            throw new IllegalStateException("Could not generate user report", exception);
        }
    }

    private void writePdf(User user, OutputStream outputStream) {
        Document document = new Document();

        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

        document.add(new Paragraph("User Report", titleFont));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("User ID: " + user.getId(), normalFont));
        document.add(new Paragraph("FirstName: " + user.getFirstName(), normalFont));
        document.add(new Paragraph("LastName: " + user.getLastName(), normalFont));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Generated at: " + LocalDateTime.now(), normalFont));

        document.close();
    }

    private String buildFileName(Long userId) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        String shortId = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        return "user-report-" + userId + "-" + timestamp + "-" + shortId + ".pdf";
    }
}
