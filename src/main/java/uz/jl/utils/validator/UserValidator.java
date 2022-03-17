package uz.jl.utils.validator;

import org.bson.types.ObjectId;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.utils.BaseUtils;

public class UserValidator extends BaseValidator<UserCreateDto, UserUpdateDto, ObjectId> {

    public UserValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void validKey(ObjectId key) throws IllegalArgumentException {

    }

    @Override
    public void validOnCreate(UserCreateDto dto) throws IllegalArgumentException {

    }

    @Override
    public void validOnUpdate(UserUpdateDto dto) throws IllegalArgumentException {

    }
}
