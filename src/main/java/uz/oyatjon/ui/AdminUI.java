package uz.oyatjon.ui;

import org.bson.types.ObjectId;
import uz.oyatjon.configs.ApplicationContextHolder;
import uz.oyatjon.configs.Session;
import uz.oyatjon.dto.user.UserCreateDto;
import uz.oyatjon.services.users.UserService;
import uz.oyatjon.utils.Print;

import static uz.oyatjon.utils.Color.RED;
import static uz.oyatjon.utils.Input.getStr;

public class AdminUI {

    private static final UserService service = ApplicationContextHolder.getBean(UserService.class);

    public static void mainAdminMenu() {
        Print.println("1. Create user");
        Print.println("2. Update user");
        Print.println("3.Update role");
        Print.println("4. Get user");
        Print.println("5. Get list of users");
        Print.println("6.Exit");
        String choice = getStr("Choose please: ");
        byAdminMenu(choice);
    }

    private static void byAdminMenu(String choice) {
        switch (choice) {
            case "1" -> createUser();
            case "2" -> mainAdminMenu();
            case "3" -> updateRole();
            case "4" -> getUser();
            case "5" -> getUserList();
            case "6" -> {
                Session.sessionUser = null;
                Menu.mainMenu();
            }
            default -> Print.println(RED, "Wrong Choice");
        }
    }

    private static void getUserList() {
        service.getList();
        mainAdminMenu();
    }

    private static void getUser() {
        String id = getStr("Enter the id please: ");
        ObjectId oId = new ObjectId(id);
        service.get(oId);
        mainAdminMenu();
    }

    private static void updateRole() {
        String id = getStr("Enter the id please: ");
        ObjectId oId = new ObjectId(id);
        service.updateRole(oId);
        mainAdminMenu();
    }

    private static void createUser() {
        String username = getStr("Username: ");
        String password = getStr("Password: ");
        String firstName = getStr("First name: ");
        String lastName = getStr("Last name: ");
        String role = getStr("Role: ");
        String language = getStr("Language: ");
        UserCreateDto dto = new UserCreateDto(username, password, firstName, lastName, role, language);
        service.create(dto);
        mainAdminMenu();
    }
}
