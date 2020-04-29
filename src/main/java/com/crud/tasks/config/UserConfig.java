package com.crud.tasks.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserConfig {

    @Value("${users.name}")
    private String userName;

    @Value("${users.mail}")
    private String userMail;
}