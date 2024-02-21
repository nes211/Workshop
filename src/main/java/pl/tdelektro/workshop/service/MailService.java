package pl.tdelektro.workshop.service;

import jakarta.mail.MessagingException;

public interface MailService {

    public void sendEmail(Long carId) throws MessagingException;
}
