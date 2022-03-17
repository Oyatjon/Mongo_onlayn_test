package uz.jl.ui;

import uz.jl.configs.ApplicationContextHolder;
import uz.jl.configs.Session;
import uz.jl.dto.question.QuestionCreateDto;
import uz.jl.entity.quiz.Variant;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Level;
import uz.jl.enums.Subject;
import uz.jl.services.question.QuestionService;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.ArrayList;
import java.util.List;

import static uz.jl.utils.Color.RED;
import static uz.jl.utils.Input.getStr;

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
