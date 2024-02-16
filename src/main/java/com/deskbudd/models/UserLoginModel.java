package com.deskbudd.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginModel {
    Long id;
    String email;
    String firstName;
    String password;
    Boolean isAdmin;
    byte[] image;
}
