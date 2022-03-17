package uz.jl.respository.question;

import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import uz.jl.criteria.GenericCriteria;
import uz.jl.dao.GenericDao;
import uz.jl.entity.quiz.Question;
import uz.jl.respository.GenericCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class QuestionRepository  extends GenericDao<GenericCriteria, Question> implements
        GenericCrudRepository<Question, ObjectId> {
    public QuestionRepository(Class<Question> clazz) {
        super(clazz);
    }


    @Override
    public ObjectId create(Question model) {
        collection.insertOne(model);
        return model.getId();
    }

    @Override
    public void update(Question model) {

    }

    @Override
    public void delete(ObjectId id) {

    }

    @Override
    public List<Question> list() {
        List<Question> quizzes = new ArrayList<>();
        collection.find().iterator().forEachRemaining(quizzes::add);
        return quizzes;
    }

    @Override
    public Optional<Question> get(ObjectId id) {
        Question question = collection.find(Filters.eq("_id", id)).first();
        return Objects.isNull(question) ? Optional.empty() : Optional.of(question);
    }
}