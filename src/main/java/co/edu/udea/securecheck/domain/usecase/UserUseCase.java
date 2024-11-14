package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.UserServicePort;
import co.edu.udea.securecheck.domain.exceptions.EmailAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.exceptions.IdentityDocumentAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.UnderageUserException;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Role;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.persistence.UserPersistencePort;
import co.edu.udea.securecheck.domain.utils.Constants;
import co.edu.udea.securecheck.domain.utils.SortQuery;
import co.edu.udea.securecheck.domain.utils.enums.RoleName;
import co.edu.udea.securecheck.domain.utils.filters.CompanyFilter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static co.edu.udea.securecheck.domain.utils.validation.ValidationUtils.validateOrThrow;

public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;

    public UserUseCase(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    public User save(User user) {
        validateUser(user);
        return userPersistencePort.save(user);
    }

    @Override
    public User createAuditor(User user) {
        user.setRole(Role.builder().name(RoleName.AUDITOR).build());
        return save(user);
    }

    @Override
    public List<Company> getUserCompanies(String id, SortQuery sort, CompanyFilter filter) {
        validateOrThrow(userPersistencePort.existsById(id),
                new EntityNotFoundException(User.class.getSimpleName(), id)
        );
        return userPersistencePort.getUserCompanies(id, sort, filter);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userPersistencePort.getUserByEmail(email);
        validateOrThrow(user != null,
                new EntityNotFoundException(String.format(Constants.USER_WITH_EMAIL_NOT_FOUND_MESSAGE, email))
        );
        return user;
    }

    @Override
    public User getUser(String id) {
        User user = userPersistencePort.getUser(id);
        validateOrThrow(user != null,
                new EntityNotFoundException(User.class.getSimpleName(), id)
        );
        return user;
    }

    private void validateUser(User user) {
        validateOrThrow(user.getBirthdate().until(LocalDateTime.now(), ChronoUnit.YEARS) >= 18,
                new UnderageUserException(user.getBirthdate())
        );
        validateOrThrow(!userPersistencePort.existsByEmail(user.getEmail()),
                new EmailAlreadyExistsException(user.getEmail())
        );
        validateOrThrow(!userPersistencePort.existsByIdentityDocument(user.getIdentityDocument()),
                new IdentityDocumentAlreadyExistsException(user.getIdentityDocument())
        );
    }
}
