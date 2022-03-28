
package uz.oyatjon.services.question;

import org.bson.types.ObjectId;
import uz.oyatjon.dto.question.QuestionCreateDto;
import uz.oyatjon.dto.question.QuestionUpdateDto;
import uz.oyatjon.dto.quiz.QuizCreateDto;
import uz.oyatjon.entity.quiz.Question;
import uz.oyatjon.mappers.question.QuestionMapper;
import uz.oyatjon.response.Data;
import uz.oyatjon.response.ResponseEntity;
import uz.oyatjon.respository.question.QuestionRepository;
import uz.oyatjon.services.AbstractService;
import uz.oyatjon.services.GenericCrudService;
import uz.oyatjon.utils.validator.QuestionValidator;

import java.util.*;

public class QuestionService extends AbstractService<QuestionRepository, QuestionMapper> implements GenericCrudService<Question, QuestionCreateDto, QuestionUpdateDto, ObjectId> {
    private final QuestionValidator validator;

    public QuestionService(QuestionRepository repository, QuestionMapper mapper, QuestionValidator questionValidator) {
        super(repository, mapper);
        this.validator = questionValidator;
    }


    @Override
    public ResponseEntity<Data<ObjectId>> create(QuestionCreateDto dto) {
        try {
            validator.validOnCreate(dto);
            Question quiz = mapper.fromCreateDto(dto);
            return new ResponseEntity<>(new Data<>(repository.create(quiz)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Data<Void>> update(QuestionUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Question>> get(ObjectId id) {
        Optional<Question> question = repository.get(new ObjectId());
        Question question1 = question.get();
        return null;
    }

    @Override
    public ResponseEntity<Data<List<Question>>> getList() {
        List<Question> list = repository.list();
        return new ResponseEntity<>(new Data<>(list));
    }

    public List<Question> getListByCriteria(QuizCreateDto quizDto) {
        List<Question> list =repository.list();
        list.removeIf(question -> !(quizDto.getLanguage().getCode().equalsIgnoreCase(question.getLanguage().getCode())
                && quizDto.getSubject().equals(question.getSubject())
                && quizDto.getLevel().equals(question.getLevel())));
        return getRandom(quizDto.getCount(), list);
    }

    private List<Question> getRandom(int count, List<Question> list) {
        if (count >= list.size())
            return list;
        List<Question> questions = new ArrayList<>();
        Set<Integer> numbers = getNumbers(count, list);
        for (Integer number : numbers) {
            questions.add(list.get(number));
            System.out.println(number);
        }
        return questions;
    }

    private Set<Integer> getNumbers(int count, List<Question> list) {
        Random rng = new Random();
        Set<Integer> generated = new LinkedHashSet<Integer>();
        while (generated.size() < count) {
            Integer next = rng.nextInt(list.size()-1) + 1;
            generated.add(next);
        }
        return generated;
    }

}
