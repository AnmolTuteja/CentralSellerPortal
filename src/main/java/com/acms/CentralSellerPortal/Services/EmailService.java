package com.acms.CentralSellerPortal.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService
{
    @Autowired
    private static JavaMailSender mailSender;

    // @Autowired
    //  private SimpleMailMessage preConfiguredMessage;

    /**
     * This method will send compose and send the message
     * */

    @Autowired
    public void setJavaMailer(JavaMailSender mailSender) {
    EmailService.mailSender=mailSender;
}
    public static void sendMail(String to, String subject, String body)
    {
        //System.out.println("in send mail..");
        SimpleMailMessage message = new SimpleMailMessage();
        //System.out.println("message= " +message);
        //System.out.println(to);
//        String To = to;
//        String Subject = subject;
//        String Body = body ;
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        //System.out.println(message);
        mailSender.send(message);
    }


}