package uz.jl.configs;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import uz.jl.entity.auth.User;
import uz.jl.entity.quiz.Question;
import uz.jl.entity.quiz.Quiz;
import uz.jl.mappers.UserMapper.UserMapper;
import uz.jl.mappers.question.QuestionMapper;
import uz.jl.mappers.quiz.QuizMapper;
import uz.jl.respository.question.QuestionRepository;
import uz.jl.respository.quiz.QuizRepository;
import uz.jl.respository.user.AuthRepository;
import uz.jl.respository.user.UserRepository;
import uz.jl.services.auth.AuthService;
import uz.jl.services.question.QuestionMarkService;
import uz.jl.services.question.QuestionService;
import uz.jl.services.quiz.QuizService;
import uz.jl.services.users.UserService;
import uz.jl.ui.AuthUI;
import uz.jl.utils.BaseUtils;
import uz.jl.utils.validator.QuestionValidator;
import uz.jl.utils.validator.UserValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ApplicationContextHolder {
    private static MongoDatabase db;
    private final static UserRepository userRepository;
    private final static UserService userService;
    private final static UserMapper userMapper;
    private final static UserValidator userValidator;
    private final static Session SESSION;
    private final static QuestionRepository questionRepository;
    private final static QuestionMapper questionMapper;
    private final static QuestionValidator questionValidator;
    private final static QuestionService questionService;
    private final static BaseUtils utils;
    private final static QuizRepository quizRepository;
    private final static QuizMapper quizMapper;
    private final static QuizService quizService;
    private final static AuthRepository authRepository;
    private final static AuthService authService;
    private final static QuestionMarkService questionMarkService;
    private final static AuthUI authUI;

    static {
        connect();
        utils = new BaseUtils();
        SESSION = new Session();
        userValidator = new UserValidator(utils);
        questionValidator = new QuestionValidator(utils);
        authUI = new AuthUI();

        userMapper = new UserMapper();
        questionMapper = new QuestionMapper();
        quizMapper = new QuizMapper();

        authRepository = new AuthRepository(User.class);
        userRepository = new UserRepository(User.class);
        questionRepository = new QuestionRepository(Question.class);
        quizRepository = new QuizRepository(Quiz.class);

        userService = new UserService(userRepository, userMapper, userValidator);
        authService = new AuthService(authRepository, userMapper, userValidator);
        questionService = new QuestionService(questionRepository, questionMapper, questionValidator);
        questionMarkService = new QuestionMarkService();
        quizService = new QuizService(quizRepository, quizMapper);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getBean(clazz.getSimpleName());
    }

    private static <T> T getBean(String beanName) {
        return switch (beanName) {
            case "MongoDatabase" -> (T) db;
            case "UserRepository" -> (T) userRepository;
            case "Session" -> (T) SESSION;
            case "UserService" -> (T) userService;
            case "UserMapper" -> (T) userMapper;
            case "UserValidator" -> (T) userValidator;
            case "QuestionRepository" -> (T) questionRepository;
            case "QuestionMapper" -> (T) questionMapper;
            case "QuestionValidator" -> (T) questionValidator;
            case "QuestionService" -> (T) questionService;
            case "QuizRepository" -> (T) quizRepository;
            case "QuizMapper" -> (T) quizMapper;
            case "QuizService" -> (T) quizService;
            case "AuthRepository" -> (T) authRepository;
            case "AuthService" -> (T) authService;
            case "QuestionMarkService" -> (T) questionMarkService;
            case "AuthUI" -> (T) authUI;
            default -> throw new RuntimeException("Bean Not Found");
        };
    }

    private static void connect() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(codecRegistry).build();

        Logger rootLogger = Logger.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        try {
            MongoClient mongoClient = MongoClients.create(clientSettings);
            db = mongoClient.getDatabase("quiz");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
