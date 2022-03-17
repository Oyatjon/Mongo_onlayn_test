package uz.jl.mappers.question;

import uz.jl.dto.GenericDto;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.dto.question.QuestionUpdateDto;
import uz.jl.entity.quiz.Question;
import uz.jl.mappers.GenericMapper;

public class QuestionMapper implements GenericMapper<Question, GenericDto, QuestionCreateDto, QuestionUpdateDto> {
    @Override
    public Question fromDto(GenericDto dto) {
        return null;
    }

    @Override
    public Question fromCreateDto(QuestionCreateDto dto) {
        return Question.childBuilder()
                .subject(dto.getSubject())
                .language(dto.getLanguage())
                .title(dto.getTitle())
                .level(dto.getLevel())
                .variants(dto.getVariants()).build();
    }

    @Override
    public Question fromUpdateDto(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public GenericDto toDto(Question model) {
        return null;
    }
}