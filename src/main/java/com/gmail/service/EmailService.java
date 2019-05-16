package com.gmail.service;

/**
 * Created by Space on 09.05.2019.
 */
public interface EmailService {
    void send(String to, String title, String body);
}
