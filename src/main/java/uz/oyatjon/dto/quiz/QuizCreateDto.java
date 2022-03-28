package uz.oyatjon.dto.quiz;

import lombok.*;
import uz.oyatjon.dto.GenericBaseDto;
import uz.oyatjon.enums.Language.Language;
import uz.oyatjon.enums.Level;
import uz.oyatjon.enums.Subject;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCreateDto implements GenericBaseDto {
    private Subject subject;
    private Level level;
    private Language language;
    private int  count;
    private boolean completed;

}
