package com.gmail.repository;

import com.gmail.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Space on 09.05.2019.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByName(String userName);

    List<User> findUsersByBirthdayDate(LocalDate birthdayDate);
}
