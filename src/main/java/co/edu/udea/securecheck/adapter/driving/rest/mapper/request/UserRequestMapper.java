package co.edu.udea.securecheck.adapter.driving.rest.mapper.request;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.UserRequest;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {
    User toDomain(UserRequest userRequest);
    List<User> toDomains(List<UserRequest> userRequests);
}
