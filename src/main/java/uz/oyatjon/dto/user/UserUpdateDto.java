package uz.oyatjon.dto.user;

import lombok.*;
import uz.oyatjon.dto.GenericDto;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto extends GenericDto {
    private String username;
    private String role;
    private String language;
    private String firstName;
    private String lastName;
}
