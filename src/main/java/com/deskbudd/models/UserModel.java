package com.deskbudd.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    @Column(name = "photo")
    private byte[] photo;

    private String firstName;

    private String lastName;

    private String Password;

    @ManyToMany
    List<ItemModel> items;
}