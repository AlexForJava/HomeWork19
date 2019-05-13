package com.gmail.util;

import com.gmail.config.JavaConfig;
import com.gmail.model.User;
import com.gmail.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

/**
 * Created by Space on 11.05.2019.
 */
public class ConsoleApp {
    public static void start() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = context.getBean(User.class);
        user.setEmail("123@urk.net");
        user.setBirthdayDate(LocalDate.now());
        user.setName("Max");
        userService.save(user);
    }
}
