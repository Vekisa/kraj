package xmlb.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {

    public JavaMailSender javaMailSender;

    public EmailSenderService(){}

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendCompleteRegistration() {
        //SimpleMailMessage mailMessage = new SimpleMailMessage();
        //mailMessage.setTo(user.getEmail());
        //mailMessage.setSubject("Complete Registration!");
        //mailMessage.setFrom("bice10izise@gmail.com");
        //mailMessage.setText("http://localhost:8080/users/registered/"+user.getConfirmationToken().getConfirmationToken()+"/confirm-account");
       // this.sendEmail(mailMessage);
    }



}
