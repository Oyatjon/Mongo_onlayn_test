package uz.oyatjon.respository.quiz;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import uz.oyatjon.configs.Session;
import uz.oyatjon.criteria.GenericCriteria;
import uz.oyatjon.dao.GenericDao;
import uz.oyatjon.entity.auth.User;
import uz.oyatjon.entity.quiz.Quiz;
import uz.oyatjon.respository.GenericCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class QuizRepository extends GenericDao<GenericCriteria, Quiz> implements
        GenericCrudRepository<Quiz, ObjectId> {
    public QuizRepository(Class<Quiz> clazz) {
        super(clazz);
    }

    @Override
    public ObjectId create(Quiz model) {
        User user = db.getCollection(User.class.getSimpleName(), User.class)
                .find(Filters.eq("_id", Session.sessionUser.getId())).first();
        if (Objects.isNull(user.getQuizzes())) {
            user.setQuizzes(new ArrayList<Quiz>());
        }
        user.getQuizzes().add(model);
        BasicDBObject dbObject = new BasicDBObject("quizzes", user.getQuizzes());
        BasicDBObject updateObject = new BasicDBObject("$set", dbObject);
        db.getCollection(User.class.getSimpleName()).updateOne(Filters.eq("_id", Session.sessionUser.getId()), updateObject);
        return model.getId();
    }

    @Override
    public void update(Quiz model) {
        User user = db.getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("_id", Session.sessionUser.getId())).first();
        List<Quiz> quizzes = user.getQuizzes();
        for (int i = 0; i < quizzes.size(); i++) {
            if(quizzes.get(i).getId().equals(model.getId())) {
                quizzes.remove(i);
                quizzes.add(model);
            }

        }
        user.setQuizzes(quizzes);
        BasicDBObject dbObject = new BasicDBObject("quizzes", user.getQuizzes());
        BasicDBObject updateObject = new BasicDBObject("$set", dbObject);
        db.getCollection(User.class.getSimpleName()).updateOne(Filters.eq("_id", Session.sessionUser.getId()), updateObject);
    }

    @Override
    public void delete(ObjectId id) {

    }

    @Override
    public List<Quiz> list() {
        User user = db.getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("_id", Session.sessionUser.getId())).first();
        return user.getQuizzes();
    }

    @Override
    public Optional<Quiz> get(ObjectId quizId) {
        User user = db.getCollection(User.class.getSimpleName(), User.class).find(Filters.eq("_id", Session.sessionUser.getId())).first();
        return user.getQuizzes().stream().filter(quiz -> quiz.getId().equals(quizId)).findAny();
    }
}
