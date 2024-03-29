package com.deskbudd.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterModelRequest {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
}
