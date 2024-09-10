package com.Email.EmailSender.Controller;

import com.Email.EmailSender.Entity.EmailRequest;
import com.Email.EmailSender.Helper.CustomeResponse;
import com.Email.EmailSender.Services.EmailServices;
import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/email")
@CrossOrigin("*")
public class EmailController {

    private EmailServices emailServices;

    public EmailController(EmailServices emailServices)
    {
        this.emailServices=emailServices;
    }




    //send email
@PostMapping("/send")
public ResponseEntity<?>sendEmail(@RequestBody EmailRequest request)
{
   emailServices.sendEmailWithHtml(request.getTo(),request.getSubject(), request.getMessage());
   return ResponseEntity.ok(CustomeResponse.builder().message("Email Send Sucessfuly!!").httpStatus(String.valueOf(HttpStatus.OK)).success(true).build());

}

@PostMapping("/send-with-file")
    public  ResponseEntity<CustomeResponse>senWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file ) throws IOException {
     emailServices.sendEmailWithFile(request.getTo(),request.getSubject(),request.getMessage(),file.getInputStream());
    return ResponseEntity.ok(CustomeResponse.builder().message("Email Send Sucessfuly!!").httpStatus(String.valueOf(HttpStatus.OK)).success(true).build());





//    @PostMapping("/send-with-file")
//    public ResponseEntity<CustomeResponse> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) throws IOException {
//        // Check if the file is not null and not empty
//        if (file == null || file.isEmpty()) {
//            return ResponseEntity.badRequest().body(CustomeResponse.builder()
//                    .message("File is missing or empty")
//                    .httpStatus(String.valueOf(HttpStatus.BAD_REQUEST))
//                    .success(false)
//                    .build());
//        }
//
//        // Proceed with sending the email
//        try (InputStream inputStream = file.getInputStream()) {
//            emailServices.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(), inputStream);
//        }
//
//        return ResponseEntity.ok(CustomeResponse.builder()
//                .message("Email Sent Successfully!")
//                .httpStatus(String.valueOf(HttpStatus.OK))
//                .success(true)
//                .build());
//    }




}


}
