package uz.jl.mappers.UserMapper;

import uz.jl.dto.GenericDto;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.auth.User;
import uz.jl.enums.Language.Language;
import uz.jl.enums.Role.Role;
import uz.jl.enums.Status.Status;
import uz.jl.mappers.GenericMapper;

public class UserMapper implements GenericMapper<User, GenericDto, UserCreateDto, UserUpdateDto> {
    @Override
    public User fromDto(GenericDto dto) {
        return null;
    }

    @Override
    public User fromCreateDto(UserCreateDto dto) {
        return User.childBuilder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(Role.getByName(dto.getRole()))
                .status(Status.NO_ACTIVE)
                .language(Language.getByCode(dto.getLanguage()))
                .build();
    }

    @Override
    public User fromUpdateDto(UserUpdateDto dto) {
        return null;
    }

    @Override
    public GenericDto toDto(User model) {
        return null;
    }
}
