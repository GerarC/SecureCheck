package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.utils.SortQuery;
import co.edu.udea.securecheck.domain.utils.filters.CompanyFilter;

import java.util.List;

public interface UserPersistencePort {
    User save(User user);
    boolean existsByEmail(String email);
    boolean existsByIdentityDocument(String identityDocument);
    boolean existsById(String id);
    User getUserByEmail(String email);
    User getUser(String id);
    List<Company> getUserCompanies(String id, SortQuery sort, CompanyFilter filter);
}
