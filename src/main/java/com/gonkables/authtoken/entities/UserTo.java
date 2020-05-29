package com.gonkables.authtoken.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class UserTo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String usermane;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;
}
