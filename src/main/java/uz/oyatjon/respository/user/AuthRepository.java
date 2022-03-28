package uz.oyatjon.respository.user;

import com.mongodb.client.model.Filters;
import uz.oyatjon.configs.ApplicationContextHolder;
import uz.oyatjon.configs.Session;
import uz.oyatjon.criteria.GenericCriteria;
import uz.oyatjon.dao.GenericDao;
import uz.oyatjon.entity.auth.User;

import java.util.Objects;
import java.util.Optional;

public class AuthRepository extends GenericDao<GenericCriteria, User>{
    private static final Session session = ApplicationContextHolder.getBean(Session.class);
    public AuthRepository(Class<User> clazz) {
        super(clazz);
    }

    public Optional<User> getByName(String name, String pass){
        User user = collection.find(Filters.and(Filters.eq("username",name),Filters.eq("password", pass))).first();
        return Objects.isNull(user) ? Optional.empty() : Optional.of(user);
    }

    public void register(User user){
        collection.insertOne(user);
    }

    public void login(User user){
        Session.sessionUser=(user);
    }


}
