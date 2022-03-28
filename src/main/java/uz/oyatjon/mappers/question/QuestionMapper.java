package uz.oyatjon.mappers.question;

import uz.oyatjon.dto.GenericDto;
import uz.oyatjon.dto.question.QuestionCreateDto;
import uz.oyatjon.dto.question.QuestionUpdateDto;
import uz.oyatjon.entity.quiz.Question;
import uz.oyatjon.mappers.GenericMapper;

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