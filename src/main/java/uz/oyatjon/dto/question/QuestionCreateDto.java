package uz.oyatjon.dto.question;

import lombok.*;
import uz.oyatjon.dto.GenericBaseDto;
import uz.oyatjon.entity.quiz.Variant;
import uz.oyatjon.enums.Language.Language;
import uz.oyatjon.enums.Level;
import uz.oyatjon.enums.Subject;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionCreateDto implements GenericBaseDto {
    private String title;
    private Level level;
    private Language language;
    private Subject subject;
    private List<Variant> variants;
}