package com.deskbudd.Controller;

import ch.qos.logback.core.model.Model;
import com.deskbudd.ControllerImpl.ControllerImpl;
import com.deskbudd.models.ImageModel;
import com.deskbudd.models.ItemModel;
import com.deskbudd.models.UserLoginModel;
import com.deskbudd.models.UserRegisterModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    private final ControllerImpl controller;

    @Autowired
    Controller(ControllerImpl controller) {
        this.controller = controller;
    }

    @GetMapping("/search")
    String findElementBySearchText(@RequestParam String searchText){
        return this.controller.searchForItemBy(searchText);
    }

    @CrossOrigin
    @GetMapping("/all")
    List<ItemModel> findAllElements(){
        return controller.getAllElements();
    }

    @CrossOrigin
    @PostMapping("/register")
    UserRegisterModelRequest registerUser(@RequestBody UserRegisterModelRequest userRegisterModelRequest){
        return this.controller.saveUser(userRegisterModelRequest);
    }

    @CrossOrigin
    @PostMapping("/login")
    UserLoginModel getExistingUser(@RequestBody UserLoginModel userLoginModel){
        return this.controller.findUser(userLoginModel);
    }
@CrossOrigin
    @PostMapping("/upload")
    public ImageModel uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
    byte[] byteArr = file.getBytes();
     this.controller.saveImage(byteArr);
     ImageModel image = new ImageModel();
     image.setPhoto(byteArr);
     return image;
    }
}
