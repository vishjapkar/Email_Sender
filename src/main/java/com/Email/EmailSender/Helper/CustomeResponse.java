package com.Email.EmailSender.Helper;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class CustomeResponse {

    private  String message;

    private  String httpStatus;

    private boolean success=false;

}
