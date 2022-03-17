package uz.jl.dto.quiz;

import lombok.*;
import uz.jl.dto.GenericBaseDto;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Level;
import uz.jl.enums.Subject;

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
