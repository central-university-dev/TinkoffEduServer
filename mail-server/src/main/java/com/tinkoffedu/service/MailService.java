package com.tinkoffedu.service;

import com.tinkoffedu.dto.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(Mail mail) {
        try {
            mailSender.send(buildMailMessage(mail));
        } catch (Exception e) {
            log.error("Unable to send message for recipient {}", mail.recipient());
        }
    }

    private SimpleMailMessage buildMailMessage(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(mail.recipient());
        message.setSubject(mail.subject());
        message.setText(mail.message());
        return message;
    }
}
