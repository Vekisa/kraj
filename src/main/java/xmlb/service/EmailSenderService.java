package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xmlb.model.User.User;

@Service("emailSenderService")
public class EmailSenderService {

    public JavaMailSender javaMailSender;

    public EmailSenderService(){}

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void sendCompleteRegistration(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("bezbednostprojekat@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "https://localhost:8443/api/auth/confirmReg?token=" + user.getVerificationToken().getVerificationToken());
        this.sendEmail(mailMessage);
    }



}
