package co.edu.udea.securecheck.configuration;

import co.edu.udea.securecheck.domain.api.*;
import co.edu.udea.securecheck.domain.api.security.AuthenticationServicePort;
import co.edu.udea.securecheck.domain.api.security.TokenServicePort;
import co.edu.udea.securecheck.domain.spi.persistence.*;
import co.edu.udea.securecheck.domain.usecase.*;
import co.edu.udea.securecheck.domain.usecase.security.AuthenticationUseCase;
import co.edu.udea.securecheck.domain.usecase.security.TokenUseCase;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.security.AuthenticationSecurityPort;
import co.edu.udea.securecheck.domain.spi.security.TokenSecurityPort;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Generated
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private static final String ROLE_PREFIX = "ROLE_";

    @Bean
    public UserServicePort userServicePort(UserPersistencePort userPersistencePort) {
        return new UserUseCase(userPersistencePort);
    }

    @Bean
    public DomainServicePort domainServicePort(DomainPersistencePort domainPersistencePort) {
        return new DomainUseCase(domainPersistencePort);
    }

    @Bean
    public ControlServicePort controlServicePort(ControlPersistencePort controlPersistencePort) {
        return new ControlUseCase(controlPersistencePort);
    }

    @Bean
    public CompanyServicePort companyServicePort(
            CompanyPersistencePort companyPersistencePort,
            UserPersistencePort userPersistencePort,
            DefaultQuestionPersistencePort defaultQuestionPersistencePort,
            CustomQuestionPersistencePort customQuestionPersistencePort
    ) {
        return new CompanyUseCase(
                companyPersistencePort,
                userPersistencePort,
                defaultQuestionPersistencePort,
                customQuestionPersistencePort
        );
    }

    @Bean
    public AuditServicePort auditServicePort(
            AuditPersistencePort auditPersistencePort,
            CompanyPersistencePort companyPersistencePort,
            ControlPersistencePort controlPersistencePort,
            AnswerPersistencePort answerPersistencePort
    ) {
        return new AuditUseCase(
                auditPersistencePort,
                companyPersistencePort,
                controlPersistencePort,
                answerPersistencePort
        );
    }

    @Bean
    public QuestionServicePort questionServicePort(
            CustomQuestionPersistencePort customQuestionPersistencePort,
            ControlPersistencePort controlPersistencePort,
            CompanyPersistencePort companyPersistencePort
    ) {
        return new QuestionUseCase(
                customQuestionPersistencePort,
                controlPersistencePort,
                companyPersistencePort
        );
    }

    @Bean
    public AuditFormUseCase auditFormUseCase(
            AuditPersistencePort auditPersistencePort,
            CompanyPersistencePort companyPersistencePort,
            DomainPersistencePort domainPersistencePort,
            ControlPersistencePort controlPersistencePort,
            AnswerPersistencePort answerPersistencePort
    ) {
        return new AuditFormUseCase(
                auditPersistencePort,
                companyPersistencePort,
                domainPersistencePort,
                controlPersistencePort,
                answerPersistencePort
        );
    }

    // Security
    @Bean
    UserDetailsService userDetailsService(UserServicePort userServicePort) {
        return username -> {
            User domainUser = userServicePort.getUser(username);
            return new org.springframework.security.core.userdetails.User(
                    domainUser.getId(),
                    domainUser.getPassword(),
                    List.of(new SimpleGrantedAuthority(ROLE_PREFIX + domainUser.getRole().getName().name()))
            );
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public TokenServicePort tokenServicePort(TokenSecurityPort tokenSecurityPort) {
        return new TokenUseCase(tokenSecurityPort);
    }

    @Bean
    AuthenticationServicePort authenticationServicePort(
            TokenSecurityPort tokenSecurityPort,
            AuthenticationSecurityPort authenticationSecurityPort,
            UserPersistencePort userPersistencePort
    ) {
        return new AuthenticationUseCase(tokenSecurityPort, authenticationSecurityPort, userPersistencePort);
    }
}
