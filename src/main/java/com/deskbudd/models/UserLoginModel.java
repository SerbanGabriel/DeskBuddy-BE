package com.deskbudd.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserLoginModel {
    Long id;
    String email;
    String firstName;
    String password;
    Boolean isAdmin;
    byte[] image;
    List<ItemModel> items;
}
