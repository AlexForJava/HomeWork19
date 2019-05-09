package com.gmail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Space on 09.05.2019.
 */
@Log4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String title, String body) {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(body);
        } catch (MessagingException e) {
            log.error("EmailService method send ", e);
        }
    }
}
