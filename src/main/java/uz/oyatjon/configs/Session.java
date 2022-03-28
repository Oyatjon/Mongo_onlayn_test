package uz.oyatjon.configs;

import lombok.Getter;
import lombok.Setter;
import uz.oyatjon.entity.auth.User;

@Getter
@Setter
public class Session {
    public static User sessionUser;
}
