package uz.jl.services.question;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.entity.quiz.Question;
import uz.jl.entity.quiz.QuestionMark;

import java.util.ArrayList;
import java.util.List;

public class QuestionMarkService {

    private final QuestionService questionService = ApplicationContextHolder.getBean(QuestionService.class);

    public List<QuestionMark> getList(QuizCreateDto quizDto) {
        List<QuestionMark> questionMarks = new ArrayList<>();
        List<Question> list = questionService.getListByCriteria(quizDto);
        for (Question question : list) {
            questionMarks.add(QuestionMark.childBuilder().question(question).build());
        }
        return questionMarks;
    }



}

