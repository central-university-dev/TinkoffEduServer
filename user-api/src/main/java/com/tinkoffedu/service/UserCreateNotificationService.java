package com.tinkoffedu.service;

import com.tinkoffedu.dto.Mail;
import com.tinkoffedu.dto.user.UserRequest;
import com.tinkoffedu.utils.DataEncryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserCreateNotificationService {

    private static final String SUBJECT = "Chipi-chipi chapa-chapa dubi-dubi daba-daba";
    private static final String MESSAGE = """
        %s %s, вы зарегистрировались на Chipi-chipi-chapa-chapa!
                
        Для дальнейшей работы подключитесь к нашему telegram-боту %s.
        Для этого перейдите к боту и введите туда свой персональный токен:
        
        %s
                
        НИКОМУ НЕ ПЕРЕДАВАЙТЕ ЭТИ ДАННЫЕ, А ТО БУДЕТ JOPA!
        """;

    private final MailService mailService;

    @Value("${data.encrypt.secret-key}")
    private String secretKey;
    @Value("${mentee.bot.username}")
    private String botUsername;

    public void notify(Long id, UserRequest user) {
        var mail = Mail.builder()
            .recipient(user.email())
            .subject(SUBJECT)
            .message(MESSAGE.formatted(user.firstName(), user.lastName(), botUsername, createToken(id)))
            .build();
        mailService.sendMail(mail);
    }

    private String createToken(Long id) {
        return DataEncryptUtils.encrypt(Collections.singletonMap("id", id), secretKey);
    }

}
