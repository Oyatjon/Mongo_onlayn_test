package uz.oyatjon.ui;

import uz.oyatjon.configs.ApplicationContextHolder;
import uz.oyatjon.configs.Session;
import uz.oyatjon.dto.question.QuestionCreateDto;
import uz.oyatjon.entity.quiz.Variant;
import uz.oyatjon.enums.Language.Language;
import uz.oyatjon.enums.Level;
import uz.oyatjon.enums.Subject;
import uz.oyatjon.services.question.QuestionService;
import uz.oyatjon.utils.Input;
import uz.oyatjon.utils.Print;

import java.util.ArrayList;
import java.util.List;

import static uz.oyatjon.utils.Color.RED;
import static uz.oyatjon.utils.Input.getStr;

public class QuestionUI {

    private static final QuestionService service = ApplicationContextHolder.getBean(QuestionService.class);

    public static void menu(){
        Print.println("1. Create Test");
        Print.println("2.Exit");
        String choice = getStr("Choose please: ");
        byChoice(choice);
    }

    private static void byChoice(String choice) {
        switch (choice) {
            case "1" -> createQuestion();
            case "2" ->{
                Session.sessionUser = null;
                Menu.mainMenu();
            }
            default -> Print.println(RED,"Wrong Choice");
        }
    }

    public static void createQuestion() {
        QuestionCreateDto build = QuestionCreateDto.builder().title(Input.getStr("Question title : "))
                .level(Level.getLevel(Input.getStr("Question Level : ")))
                .language(Language.getByCode(Input.getStr("Language : ")))
                .subject(Subject.getValue(Input.getStr("Subject : "))).build();
        List<Variant> variants = createVariant(new ArrayList<>());
        build.setVariants(variants);
        service.create(build);
    }

    public static List<Variant> createVariant( List<Variant> list) {

        String choose = Input.getStr("Add variant ? (quit) ");
        if (choose.startsWith("q")) return list;
        list.add(Variant.childBuilder().answer(Input.getStr("Answer : "))
                .correct(Input.getStr("Is true answer : (y/n) ").startsWith("y")).build());
        return createVariant(list);
    }
}
