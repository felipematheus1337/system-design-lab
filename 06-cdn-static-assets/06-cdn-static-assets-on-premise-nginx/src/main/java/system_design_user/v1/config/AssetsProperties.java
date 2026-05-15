package system_design_user.v1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "assets")
public record AssetsProperties (String basePath,
                                String publicBaseUrl) {
}
