package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.utils.SortQuery;
import co.edu.udea.securecheck.domain.utils.filters.CompanyFilter;

import java.util.List;

public interface UserServicePort {
    User createAuditor(User user);
    List<Company> getUserCompanies(String id, SortQuery sort, CompanyFilter filter);
    User getUserByEmail(String email);
    User getUser(String id);
}
