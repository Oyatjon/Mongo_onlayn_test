package uz.oyatjon.mappers.UserMapper;

import uz.oyatjon.dto.GenericDto;
import uz.oyatjon.dto.user.UserCreateDto;
import uz.oyatjon.dto.user.UserUpdateDto;
import uz.oyatjon.entity.auth.User;
import uz.oyatjon.enums.Language.Language;
import uz.oyatjon.enums.Role.Role;
import uz.oyatjon.enums.Status.Status;
import uz.oyatjon.mappers.GenericMapper;

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
