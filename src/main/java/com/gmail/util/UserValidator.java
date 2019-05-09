package com.gmail.util;

import com.gmail.model.User;
import com.gmail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Space on 09.05.2019.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "empty");
        User user = (User) o;
        if (user.getRandom() < 20  || user.getRandom() > 30) {
            errors.rejectValue("random", "incorrectly generated random number");
        }
    }
}
