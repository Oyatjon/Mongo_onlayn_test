package uz.jl.utils.validator;

import uz.jl.utils.BaseUtils;

import java.io.Serializable;

public abstract class AbstractValidator<K extends Serializable> {
    protected final BaseUtils utils;

    protected AbstractValidator(BaseUtils utils) {
        this.utils = utils;
    }

    protected abstract void validKey(K key) throws IllegalArgumentException;

}
