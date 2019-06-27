package modul.oauth.security;

import modul.oauth.dto.AgentDTO;
import modul.oauth.model.User;
import modul.oauth.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

    public void sendCompleteRegistration(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("bezbednostprojekat@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://localhost:8762/uua/user/auth/confirmToken?token=" + user.getVerificationToken().getVerificationToken());
        this.sendEmail(mailMessage);
    }

    public void sendCompleteAgentRegistration(AgentDTO user, VerificationToken verificationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("bezbednostprojekat@gmail.com");
        mailMessage.setText("Hello " + user.getUsername() + " Welcome to MegaTravel ," +
                " To confirm your Agent account, please click here : " + "http://localhost:8762/uua/user/auth/confirmToken?token=" + verificationToken.getVerificationToken() +
        "Your temporary password :" +user.getPassword() );

        this.sendEmail(mailMessage);
    }

//    public void sendWrongPassword(User user) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Security alert!");
//        mailMessage.setFrom("bezbednostprojekat@gmail.com");
//        Date pom = user.getDateBlock();
//        Date pom1 = new Date(pom.getTime() + (24 * 60 * 60 * 1000));
//        mailMessage.setText("Someone tried to sign into Your account with wrong password three times. Your account is blocked until " + pom1 + " .");
//        this.sendEmail(mailMessage);
//    }

}
