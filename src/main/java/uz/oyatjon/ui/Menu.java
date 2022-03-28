package uz.oyatjon.ui;

import uz.oyatjon.configs.Session;
import uz.oyatjon.enums.Role.Role;
import uz.oyatjon.utils.Input;
import uz.oyatjon.utils.Print;

public class Menu {
    public static void mainMenu() {
        if (Session.sessionUser == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Log out");
            String choose = Input.getStr("? : ");
            switch (choose) {
                case "1" -> AuthUI.login();
                case "2" -> AuthUI.register();
                case "3" -> {
                    Print.println("Good bye");
                    return;
                }
            }
        }else if(Session.sessionUser.getRole().equals(Role.ADMIN)){
            AdminUI.mainAdminMenu();
        }else if(Session.sessionUser.getRole().equals(Role.TEACHER)){
            QuestionUI.menu();
        }else if(Session.sessionUser.getRole().equals(Role.STUDENT)){
            QuizUI.menu();
        }
        mainMenu();
    }
}
