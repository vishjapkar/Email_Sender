package com.Email.EmailSender.Entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmailRequest {

    private  String to;

    private  String subject;

    private  String message;









}
