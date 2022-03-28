package uz.oyatjon.ui;

import org.bson.types.ObjectId;
import uz.oyatjon.configs.ApplicationContextHolder;
import uz.oyatjon.configs.Session;
import uz.oyatjon.dto.quiz.QuizCreateDto;
import uz.oyatjon.entity.quiz.Question;
import uz.oyatjon.entity.quiz.QuestionMark;
import uz.oyatjon.entity.quiz.Quiz;
import uz.oyatjon.entity.quiz.Variant;
import uz.oyatjon.enums.Language.Language;
import uz.oyatjon.enums.Level;
import uz.oyatjon.enums.Subject;
import uz.oyatjon.response.Data;
import uz.oyatjon.response.ResponseEntity;
import uz.oyatjon.services.quiz.QuizService;
import uz.oyatjon.utils.Color;
import uz.oyatjon.utils.Input;
import uz.oyatjon.utils.Print;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static uz.oyatjon.utils.Color.*;
import static uz.oyatjon.utils.Input.getStr;

public class QuizUI {
    private static QuizService quizService = ApplicationContextHolder.getBean(QuizService.class);

    public static void menu() {
        Print.println("1. Start Test");
        Print.println("2. Show all my result");
        Print.println("3.Exit");
        String choice = getStr("Choose please: ");
        switch (choice) {
            case "1" -> createQuiz();
            case "2" -> showAllQuiz();
            case "3" -> {
                Session.sessionUser = null;
                Menu.mainMenu();
            }
            default -> Print.println(RED, "Wrong Choice");
        }
        menu();
    }

    public static void createQuiz() {
        String subject = Input.getStr("Enter the subject: ");
        String level = Input.getStr("Enter the level: ");
        String language = Input.getStr("Enter the language: ");
        Integer count = Input.getNum("Enter the count: ");
        QuizCreateDto quizCreateDto = QuizCreateDto.builder().subject(Subject.getValue(subject))
                .level(Level.getLevel(level)).language(Language.getByCode(language)).count(count).build();
        ResponseEntity<Data<ObjectId>> quizId = quizService.create(quizCreateDto);
        String choice = Input.getStr("Are you ready to start test? Yes/No: ");
        if (choice.startsWith("Y") || choice.startsWith("y")) {
            Print.println(YELLOW, "==================Start======================");
            answerQuiz(quizId.getData().getBody());
        } else Print.println(Color.YELLOW, "End the process...");
    }


    private static void answerQuiz(ObjectId quizId) {
        Quiz quiz = quizService.get(quizId).getData().getBody();
        int count = 1;
        for (int i = 0; i < quiz.getQuestionsMarks().size(); i++) {
            Question question = quiz.getQuestionsMarks().get(i).getQuestion();
            Print.println(count++ + ". " + question.getTitle());
            for (int j = 0; j < question.getVariants().size(); j++) {
                Print.println((char) (65 + j) + ". " + question.getVariants().get(j).getAnswer());
            }
            char answer = getStr("Your Answer (To complete the test, write FINISH): ").toUpperCase(Locale.ROOT).charAt(0);
            if (answer == 70) {
                break;
            }
            Variant variant = question.getVariants().get(answer - 65);
            quiz.getQuestionsMarks().get(i).setVariant(variant);
            quiz.getQuestionsMarks().get(i).setRight(variant.isCorrect());
        }
        Print.println(YELLOW, "==================End======================");
        quizService.update(quiz);
        showResults(quiz);
    }

    private static void showResults(Quiz quiz) {
        int mark = 0;
        Print.println(YELLOW, "==================Your results======================");
        for (int i = 0; i < quiz.getQuestionsMarks().size(); i++) {
            if (quiz.getQuestionsMarks().get(i).isRight()) {
                Print.print(i + 1 + ". " + quiz.getQuestionsMarks().get(i).getVariant().getAnswer());
                Print.println(GREEN, " ✔");
                mark++;
            } else {
                Print.print(Color.RED_COMPLETED, i + 1 + ". " + quiz.getQuestionsMarks().get(i).getVariant().getAnswer());
                Print.println(RED, " ❌");
                for (Variant variant : quiz.getQuestionsMarks().get(i).getQuestion().getVariants()) {
                    if (variant.isCorrect()) {
                        Print.print(Color.GREEN, "Correct answers is : ");
                        Print.println(Color.YELLOW, variant.getAnswer());
                    }
                }
            }
        }
        Print.println(YELLOW, "===============Your total mark: " + mark + " ======================");
    }

    public static void showAllQuiz() {
        List<Quiz> quizzes = quizService.getList().getData().getBody();
        int count = 1;
        for (Quiz quiz : quizzes) {
            int answers = 0;
            for (QuestionMark questionsMark : quiz.getQuestionsMarks()) {
                if (questionsMark.isRight())
                    answers++;
            }
            Print.print(Color.YELLOW, count + ". " + quiz.getSubject()+" -> "+ new SimpleDateFormat("yyyy-MM-dd HH:mm").format(quiz.getCreatedAt()));
            Print.println(" \n\t\t Count of right answers: " + answers);
            count++;
        }
    }
}
