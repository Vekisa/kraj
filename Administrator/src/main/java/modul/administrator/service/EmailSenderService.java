package modul.administrator.service;

import modul.administrator.model.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("emailSenderService")
public class EmailSenderService {

    public JavaMailSender javaMailSender;

    public EmailSenderService() {
    }

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void sendAgentRequest(String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("bezbednostprojekat@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "https://localhost:8080/agent");
        this.sendEmail(mailMessage);
    }

    public void sendCompleteRegistration(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("bezbednostprojekat@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:9100/api/auth/confirmReg?token=" + user.getVerificationToken().getVerificationToken());
        this.sendEmail(mailMessage);
    }

}
