package uz.oyatjon.services.quiz;

import org.bson.types.ObjectId;
import uz.oyatjon.configs.ApplicationContextHolder;
import uz.oyatjon.dto.GenericDto;
import uz.oyatjon.dto.quiz.QuizCreateDto;
import uz.oyatjon.entity.quiz.QuestionMark;
import uz.oyatjon.entity.quiz.Quiz;
import uz.oyatjon.mappers.quiz.QuizMapper;
import uz.oyatjon.response.Data;
import uz.oyatjon.response.ResponseEntity;
import uz.oyatjon.respository.quiz.QuizRepository;
import uz.oyatjon.services.AbstractService;
import uz.oyatjon.services.GenericCrudService;
import uz.oyatjon.services.question.QuestionMarkService;

import java.util.List;

public class QuizService extends AbstractService<QuizRepository, QuizMapper> implements GenericCrudService<Quiz, QuizCreateDto, GenericDto, ObjectId> {

    private final QuestionMarkService questionMarkService = ApplicationContextHolder.getBean(QuestionMarkService.class);

    public QuizService(QuizRepository repository, QuizMapper mapper) {
        super(repository, mapper);
    }

   /* public List<Quiz> getList() {

    }*/

    @Override
    public ResponseEntity<Data<ObjectId>> create(QuizCreateDto quizDto) {
        List<QuestionMark> questionList = questionMarkService.getList(quizDto);
        Quiz quiz = Quiz.childBuilder().questionsMarks(questionList).
                id(new ObjectId()).language(quizDto.getLanguage()).
                subject(quizDto.getSubject()).level(quizDto.getLevel()).build();
        ObjectId quizId = repository.create(quiz);
        return new ResponseEntity<>(new Data<>(quizId));
    }

    @Override
    public ResponseEntity<Data<Void>> update(GenericDto dto) {
      //  repository.update(dto);
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Quiz>> get(ObjectId id) {
        return new ResponseEntity<>(new Data<>(repository.get(id).get()));
    }

    @Override
    public ResponseEntity<Data<List<Quiz>>> getList() {
        return new ResponseEntity<>(new Data<>(repository.list()));
    }

    public Quiz startTest(QuizCreateDto quizDto) {

        return null;
    }

    public void update(Quiz quizN) {
        repository.update(quizN);
    }
}
