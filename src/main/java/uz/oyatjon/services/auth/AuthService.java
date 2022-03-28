package uz.oyatjon.services.auth;

import uz.oyatjon.dto.user.UserCreateDto;
import uz.oyatjon.entity.auth.User;
import uz.oyatjon.mappers.UserMapper.UserMapper;
import uz.oyatjon.response.Data;
import uz.oyatjon.response.ResponseEntity;
import uz.oyatjon.respository.user.AuthRepository;
import uz.oyatjon.services.AbstractService;
import uz.oyatjon.utils.validator.UserValidator;

import java.util.ArrayList;
import java.util.Optional;

public class
AuthService extends AbstractService<AuthRepository, UserMapper> {
    private final UserValidator validator;

    public AuthService(AuthRepository repository, UserMapper mapper, UserValidator userValidator) {
        super(repository, mapper);
        this.validator = userValidator;
    }

    public ResponseEntity<Data<String>> login (String name, String password){
        try{
            Optional<User> byName = repository.getByName(name,password);
            if (byName.isEmpty()){
                return new ResponseEntity<>(new Data<>("You are not registered!"));
            }
            repository.login(byName.get());
            return new ResponseEntity<>(new Data<>("SuccessFully logged in"));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public ResponseEntity<Data<String>> register (UserCreateDto dto){
        try{
            validator.validOnCreate(dto);
            Optional<User> byName = repository.getByName(dto.getUsername(), dto.getPassword());
            if (byName.isPresent()){throw new IllegalArgumentException();}
            User user1 = mapper.fromCreateDto(dto);
            user1.setQuizzes(new ArrayList<>());
            repository.register(user1);
            return new ResponseEntity<>(new Data<>("SuccessFully registered"));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
