package com.Email.EmailSender.Services;

import com.Email.EmailSender.EmailInterface.EmailInterface;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class EmailServices implements EmailInterface {

    private JavaMailSender mailSender;
 //optionally we use the @Autowired also if we dont want to make constructor

    private Logger logger= LoggerFactory.getLogger(EmailServices.class);
    private String filePath;

    public EmailServices(JavaMailSender mailSender)
    {
        this.mailSender=mailSender;
    }



    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("pc.vishaljapkar@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("Email has been sent.....");



    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("pc.vishaljapkar@gmail.com");
        mailSender.send(simpleMailMessage);
        logger.info("Email sented");


    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage simpleMailMessage=mailSender.createMimeMessage();
       try{
           MimeMessageHelper helper=new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
           helper.setTo(to);
           helper.setSubject(subject);
           helper.setFrom("pc.vishaljapkar@gmail.com");
           helper.setText(htmlContent,true);
           mailSender.send(simpleMailMessage);
           logger.info("Email sended in second function");
       }catch (MessagingException e)
       {
           throw new RuntimeException(e);
       }



    }

//    @Override
//    public void sendEmailWithFile(String to, String subject, String message, InputStream file) {
//
//        MimeMessage mimeMessage=mailSender.createMimeMessage();
//        try{
//            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setFrom("pc.vishaljapkar@gmail.com");
//            helper.setText(message);
//            FileSystemResource fileSystemResource=new FileSystemResource(file);
//            helper.addAttachment(fileSystemResource.getFilename(),file);
//            mailSender.send(mimeMessage);
//            logger.info("Email sended in second function");
//        }catch (MessagingException e)
//        {
//            throw new RuntimeException(e);
//        }
//
//
//    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream file) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("pc.vishaljapkar@gmail.com");
            helper.setText(message, true);

            // Convert InputStream to InputStreamSource using ByteArrayResource
            InputStreamSource inputStreamSource = new ByteArrayResource(file.readAllBytes());

            // Add attachment with a specified filename (you can modify the filename)
            helper.addAttachment("attachment.pdf", inputStreamSource);

            // Send email
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully");
        } catch (MessagingException | IOException e) {
            throw new RuntimeException("Error while sending email with attachment", e);
        }
    }

    @Override
    public void sendEmailWithFileStreame(String to, String subject, String message, InputStream is ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("pc.vishaljapkar@gmail.com");
            helper.setText(message);
            File file = new File("src/main/resources/email/amz.png");
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), file);
            mailSender.send(mimeMessage);
            logger.info("Email sended in second function");
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
