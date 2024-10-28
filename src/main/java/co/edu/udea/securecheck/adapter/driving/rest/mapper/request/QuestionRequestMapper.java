package co.edu.udea.securecheck.adapter.driving.rest.mapper.request;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.QuestionRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.UpdateQuestionRequest;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionRequestMapper {

    default Control id2Control(Long controlId){
        return Control.builder().id(controlId).build();
    }

    default Company id2Company(String controlId){
        return Company.builder().id(controlId).build();
    }

    @Mapping(target = "control", source = "controlId")
    @Mapping(target = "company", source = "companyId")
    Question toDomain(QuestionRequest userRequest);
    Question toDomain(UpdateQuestionRequest questionRequest);
    List<Question> toDomains(List<QuestionRequest> userRequests);
}
