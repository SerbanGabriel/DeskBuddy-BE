package com.deskbudd.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestModel {

    private String title;
    private String description;
    private double price;
    private double count;
    private Long  cardId;
    private String editImage;
}