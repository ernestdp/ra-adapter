package com.ernest.reefangel.service.validators;

import com.ernest.reefangel.exceptions.RValidationException;

/**
 * Created by ernest on 2017/04/18.
 */
public interface Validator<T> {

    void validate(T value) throws RValidationException;
}
