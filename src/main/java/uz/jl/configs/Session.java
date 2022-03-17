package uz.jl.configs;

import lombok.Getter;
import lombok.Setter;
import uz.jl.entity.auth.User;

@Getter
@Setter
public class Session {
    public static User sessionUser;
}
