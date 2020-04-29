package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    @Autowired
    private MailCreatorService mailCreatorService;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }

    //public SimpleMailMessage setCc(final Mail mail) {
      //  SimpleMailMessage mailMessage = new SimpleMailMessage();
      //mailMessage.setTo(mail.getMailTo());
      //  mailMessage.setSubject(mail.getSubject());
      //  mailMessage.setText(mail.getMessage());

      //  SimpleMailMessage mailMessageWithCc = new SimpleMailMessage();
      //  mailMessageWithCc.setTo(mail.getMailTo());
      //  mailMessageWithCc.setSubject(mail.getSubject());
      //  mailMessageWithCc.setText(mail.getMessage());
      //  mailMessageWithCc.setCc(mail.getToCc());

       // if (mail.getToCc() == null) {
       //     return mailMessage;
       // } else {
       //     return mailMessageWithCc;
       // }
  //  }
}