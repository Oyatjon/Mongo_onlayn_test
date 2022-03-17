package uz.jl.dto.question;

import lombok.*;
import uz.jl.dto.GenericBaseDto;
import uz.jl.entity.quiz.Variant;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Level;
import uz.jl.enums.Subject;

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