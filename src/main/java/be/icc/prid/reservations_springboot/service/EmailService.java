package be.icc.prid.reservations_springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.application.frontend-url}")
    private String frontendUrl;

    public void sendPasswordResetMail(String to, String token) {
        String url = frontendUrl + "/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reinitialisation du mot de passe");
        message.setText("Vous avez demande la reinitialisation du mot de passe. \n\n" +
                "Cliquez sur le lien suivant pour reinitialiser votre mot de passe : \n" + url +
                "\n\nCe lien expire dans 1 heure.\nSi vous n'avez pas demande cela, ignorez cet e-mail.");

        IO.println(url); //Debug (non IA, je sais que Java a simplifie la maniere de print)

        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.err.println(e.getMessage());
            //TODO Notify user & log error
        }
    }
}
