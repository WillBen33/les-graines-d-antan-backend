package com.lgda.backend.email;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String to;
    private String subject;
    private String body;

}
