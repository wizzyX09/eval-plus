package edu.mum.evalplus.service;

public interface IEmailService {
    public void sendMail(String to, String subject, String content);
}
