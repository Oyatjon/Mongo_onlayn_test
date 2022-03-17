package uz.jl.dto.user;

import lombok.*;
import uz.jl.dto.GenericBaseDto;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto implements GenericBaseDto {
    private String username;
    private String password;
    private String role;
    private String language;
    private String firstName;
    private String lastName;
}
