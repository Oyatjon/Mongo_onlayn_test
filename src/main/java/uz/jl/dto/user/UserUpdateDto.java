package uz.jl.dto.user;

import lombok.*;
import uz.jl.dto.GenericDto;


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
