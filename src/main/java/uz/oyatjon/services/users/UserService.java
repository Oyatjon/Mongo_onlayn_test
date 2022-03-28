package uz.oyatjon.services.users;

import org.bson.types.ObjectId;
import uz.oyatjon.dto.user.UserCreateDto;
import uz.oyatjon.dto.user.UserUpdateDto;
import uz.oyatjon.entity.auth.User;
import uz.oyatjon.mappers.UserMapper.UserMapper;
import uz.oyatjon.response.Data;
import uz.oyatjon.response.ResponseEntity;
import uz.oyatjon.respository.user.UserRepository;
import uz.oyatjon.services.AbstractService;
import uz.oyatjon.services.GenericCrudService;
import uz.oyatjon.utils.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

public class UserService extends AbstractService<UserRepository, UserMapper> implements GenericCrudService<User, UserCreateDto, UserUpdateDto, ObjectId> {

    private final UserValidator validator;

    public UserService(UserRepository repository, UserMapper mapper, UserValidator userValidator) {
        super(repository, mapper);
        this.validator = userValidator;
    }


    @Override
    public ResponseEntity<Data<ObjectId>> create(UserCreateDto dto) {
        try {
            validator.validOnCreate(dto);
            User user = mapper.fromCreateDto(dto);
            user.setQuizzes(new ArrayList<>());
            return new ResponseEntity<>(new Data<>(repository.create(user)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Data<Void>> update(UserUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Void>> delete(ObjectId id) {
        try{
            repository.delete(id);
            return new ResponseEntity<>(new Data<>(null));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Data<User>>get(ObjectId id) {
        try{
            User user = repository.get(id).get();
            return new ResponseEntity<>(new Data<>(user));
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseEntity<Data<List<User>>> getList() {
        try {
            List<User>  users = repository.list();
            return new ResponseEntity<>(new Data<>(users));

        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public ResponseEntity<Data<Void>> updateRole(ObjectId id) {
        try{
            repository.updateRole(id);
            return new ResponseEntity<>(new Data<>(null));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
