package com.gonkables.authtoken.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto implements Serializable {

    private static final long serialVersionUID = 1781904469902702475L;
    private String login;
    private char[] password;
}
