package uz.jl.services.quiz;

import org.bson.types.ObjectId;
import uz.jl.configs.ApplicationContextHolder;
import uz.jl.dto.GenericDto;
import uz.jl.dto.quiz.QuizCreateDto;
import uz.jl.entity.quiz.Question;
import uz.jl.entity.quiz.QuestionMark;
import uz.jl.entity.quiz.Quiz;
import uz.jl.entity.quiz.Variant;
import uz.jl.mappers.quiz.QuizMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.respository.quiz.QuizRepository;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCrudService;
import uz.jl.services.question.QuestionMarkService;

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
