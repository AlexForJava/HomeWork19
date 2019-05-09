package com.gmail.service;

import com.gmail.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Space on 09.05.2019.
 */
@Service
@Log4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShcedulerService {

    private final UserService userService;
    private final EmailService emailService;

    @Scheduled(cron = "*/10 * * * * *")
    public void sendMailToUsers() {
        LocalDate localDate = LocalDate.now();
        List<User> users = userService.getByBirthdayDate(localDate);
        if (!users.isEmpty()) {
            users.forEach(user -> {
                        try {
                            String message = "Happy Birthday dear " + user.getName() + "!";
                            emailService.send(user.getEmail(), "Happy birthday", message);
                            log.info("Email have been sent to " + user.getName());
                        } catch (Exception e) {
                            log.error("Email can't be sent", e);
                        }
                    }
            );
        }
    }
}
