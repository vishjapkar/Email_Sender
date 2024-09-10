package com.Email.EmailSender.emailTest;

import com.Email.EmailSender.Services.EmailServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
public class Email_Test{


    @Autowired
    private EmailServices emailServices;


    @Test
    void  emailSend()
    {
        System.out.println("sending email");

        emailServices.sendEmail("japkarvishal@aca.edu.in","Email From Springboot Presentation", "This is the springboot project testing");
    }

    @Test
    void sendEmailWithHtml(){
        System.out.println("Email html sended");
        //String html="";
        emailServices.sendEmailWithHtml("japkarvishal97@gmail.com"," HTML Email From Springboot","<h1 style='color:purple;border:1px solid blue;'>Welcome Vishal in SpringBoot </h1>"+" dhingana");
    }

//
//    @Test
//    void sendEmailWithFile(){
//         emailServices.sendEmailWithFile("japkarvishal97@gmail.com",
//                 "Email With file",
//                 "This email Contains File",
//                  new File("C:\\Users\\user\\OneDrive\\Desktop\\mumbaitai.pdf")
//
//        );
//    }


    @Test
    void sendEmailWithFile() {
        try {
            // Convert the File to an InputStream
            File file = new File("C:\\Users\\user\\OneDrive\\Desktop\\mumbaitai.pdf");
            InputStream inputStream = new FileInputStream(file);

            // Call the method with InputStream
            emailServices.sendEmailWithFile(
                    "japkarvishal97@gmail.com",
                    "Email With File",
                    "This email contains a file attachment",
                    inputStream
            );

            // You may also assert or verify behaviors if needed
            // For example, you can verify if the email was sent successfully using a mock mailSender

        } catch (IOException e) {
            e.printStackTrace();
            //fail("Test failed due to IOException: " + e.getMessage());
        }
    }



    @Test
    void sendEmailWithFileStreame(){
         File file=new File("C:\\Users\\user\\OneDrive\\Desktop\\mumbaitai.pdf");
         try{
             InputStream is=new FileInputStream(file);
             emailServices.sendEmailWithFileStreame("japkarvishal97@gmail.com",
                     "Email With file",
                     "This email Contains File",is
                     );

         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }


    }



}
