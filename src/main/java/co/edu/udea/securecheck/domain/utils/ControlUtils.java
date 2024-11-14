package co.edu.udea.securecheck.domain.utils;

import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.model.AnsweredControl;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Question;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ControlUtils {
    private ControlUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<AnsweredControl> mapAnsweredControl(Set<Control> controls, List<Question> questions, List<Answer> answers) {
        return controls.stream().map(control -> AnsweredControl.builder()
                        .id(control.getId())
                        .answer(StreamUtils.findAny(
                                answers, answer -> answer.getControl().getId().equals(control.getId()),
                                new EntityNotFoundException(Question.class.getSimpleName(), control.getId().toString())
                        ))
                        .description(control.getDescription())
                        .name(control.getName())
                        .index(control.getIndex())
                        .questions(questions.stream().filter(
                                question -> question.getControl().getId().equals(control.getId())).toList())
                        .build())
                .sorted(Comparator.comparing(AnsweredControl::getIndex))
                .toList();
    }
}
