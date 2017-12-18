package edu.mum.evalplus.service;

import edu.mum.evalplus.util.MailMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IEmailServiceImpl implements IEmailService {
    @Autowired
    private MailMail mailSender;

    public void sendMail(String to, String subject, String content) {
        mailSender.sendMail(to, subject, content);


    }

}
