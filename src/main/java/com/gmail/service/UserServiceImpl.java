package com.gmail.service;

import com.gmail.repository.UserRepository;
import com.gmail.exceptions.UserNotFoundException;
import com.gmail.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Space on 09.05.2019.
 */
@Log4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        try {
            return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            log.error("UserServiceImpl method - getById ", e);
            return new User();
        }
    }

    @Override
    public List<User> getByName(String name) {
        return userRepository.findUsersByName(name);
    }

    @Override
    public List<User> getByBirthdayDate(LocalDate birthdayDate) {
        return userRepository.findUsersByBirthdayDate(birthdayDate);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
