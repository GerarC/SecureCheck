package co.edu.udea.securecheck.configuration;

import co.edu.udea.securecheck.domain.utils.Generated;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Generated
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${app.description}") String appDescription,
            @Value("${app.version}") String appVersion
                                 ) {
        return new OpenAPI()
                .info(new Info()
                        .title("SecureCheck")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("https://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                );
    }
}
