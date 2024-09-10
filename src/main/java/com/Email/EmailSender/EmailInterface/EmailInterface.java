package com.Email.EmailSender.EmailInterface;

import java.io.File;
import java.io.InputStream;

public interface EmailInterface {
    void sendEmail(String to, String subject,String message);

    //send email to multiple person
    void sendEmail(String[] to, String subject,String message);

    //void send email with html

    void sendEmailWithHtml(String to, String subject,String htmlContent);

    //void send email with file

    void sendEmailWithFile(String to, String subject, String message, InputStream file);

    //now use the buffer class for taking the input

    void sendEmailWithFileStreame(String to, String subject, String message, InputStream is);


}
