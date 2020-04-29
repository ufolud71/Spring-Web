package com.crud.tasks.service;

import com.crud.tasks.config.UserConfig;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality= new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");


        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://ufolud71.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_company", adminConfig.getCompanyName());
        context.setVariable("admin_company_email", adminConfig.getCompanyEmail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String countTaskInTrelloEmail(String message){
        Context context = new Context();
        context.setVariable("user_config", userConfig);
        context.setVariable("friend", false);
        context.setVariable("message", message);
        context.setVariable("count_task_url", "http://localhost:8080/v1/trello/getTrelloBoards");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye", adminConfig.getAdminName());
        context.setVariable("admin", adminConfig);

        return templateEngine.process("mail/count-trello-tasks", context);
    }
}
