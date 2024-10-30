package co.edu.udea.securecheck.adapter.driving.rest.mapper.response;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AnswerResponse;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerResponseMapper {
    List<AnswerResponse> toResponses(List<Answer> answers);
}
