package com.gmail;

import com.gmail.config.JavaConfig;
import com.gmail.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.getByBirthdayDate(LocalDate.now()).forEach(System.out::println);
    }
}
