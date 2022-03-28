package uz.oyatjon.services.question;

import uz.oyatjon.configs.ApplicationContextHolder;
import uz.oyatjon.dto.quiz.QuizCreateDto;
import uz.oyatjon.entity.quiz.Question;
import uz.oyatjon.entity.quiz.QuestionMark;

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

