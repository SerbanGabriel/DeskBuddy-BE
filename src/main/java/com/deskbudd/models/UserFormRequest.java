package com.deskbudd.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserFormRequest {
    String email;
    String firstName;
    String lastName;
    String password;
}
