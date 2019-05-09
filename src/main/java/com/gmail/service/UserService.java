package com.gmail.service;

import com.gmail.model.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Space on 09.05.2019.
 */
public interface UserService {
    void save(User user);

    User getById(Long id);

    List<User> getByName(String name);

    List<User> getByBirthdayDate(LocalDate birthdayDate);

    void deleteUser(User user);

    void deleteById(Long id);

}
