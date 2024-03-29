package com.deskbudd.Controller;

import ch.qos.logback.core.model.Model;
import com.deskbudd.ControllerImpl.ControllerImpl;
import com.deskbudd.models.ImageModel;
import com.deskbudd.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Controller {

    private final ControllerImpl controller;

    @Autowired
    Controller(ControllerImpl controller) {
        this.controller = controller;
    }

    @GetMapping("/search")
    String findElementBySearchText(@RequestParam String searchText) {
        return this.controller.searchForItemBy(searchText);
    }

    @CrossOrigin
    @GetMapping("/all")
    List<ItemModel> findAllElements() {
        return controller.getAllElements();
    }

    @CrossOrigin
    @PostMapping("/register")
    UserRegisterModelRequest registerUser(@RequestBody UserRegisterModelRequest userRegisterModelRequest) {
        return this.controller.saveUser(userRegisterModelRequest);
    }

    @CrossOrigin
    @PostMapping("/login/{email}")
    UserLoginModel getExistingUser(@PathVariable String email) {
        return this.controller.findUserEmail(email);
    }

    @CrossOrigin
    @PostMapping("/upload/{id}")
    public ImageModel uploadImage(@RequestParam("image") MultipartFile file,@PathVariable Long id) throws IOException {
        byte[] byteArr = file.getBytes();
        this.controller.saveImage(byteArr,id);
        ImageModel image = new ImageModel();
        image.setPhoto(byteArr);
        return image;
    }

    @CrossOrigin
    @PutMapping("/saveUserData/{id}")
    public UserLoginModel uploadItems(@PathVariable Long id, @RequestBody UserFormRequest userFormRequest){
        return this.controller.updateUsers(id,userFormRequest);
    }

    @CrossOrigin
    @PostMapping(value = "/preview", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ImageModel previewCard(@RequestPart String user, @RequestPart Optional<MultipartFile> firstImage) throws IOException {
        ImageModel image = new ImageModel();
            byte[] byteArr = firstImage.get().getBytes();
            image.setPhoto(byteArr);
        return image;
    }

    @CrossOrigin
    @DeleteMapping("deleteItem/{id}")
        public void deleteItem(@PathVariable Long id){
        this.controller.delete(id);
        }


    @CrossOrigin
    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public void saveCard(@RequestPart String user, @RequestPart Optional<MultipartFile> firstImage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ItemRequestModel item = objectMapper.readValue(user, ItemRequestModel.class);

        ItemModel itemModel = new ItemModel();
        itemModel.setDescription(item.getDescription());
        itemModel.setTitle(item.getTitle());
        itemModel.setPrice(item.getPrice());
        itemModel.setCount(item.getCount());
        itemModel.setId(item.getCardId());
        if(firstImage.isPresent()){
            byte[] img1 = firstImage.get().getBytes();
            itemModel.setImage1(img1);

        }
        this.controller.saveItem(itemModel);
    }


    @CrossOrigin
    @PostMapping("addToCart/{itemId}/{userId}")
    public void addToCart(@PathVariable Long itemId, @PathVariable Long userId){
        this.controller.addToCart(itemId, userId);
    }

    @CrossOrigin
    @GetMapping("userItems/{userId}")
    public UserLoginModel addToCart(@PathVariable Long userId){
        return this.controller.findUserById(userId);
    }

    @CrossOrigin
    @GetMapping("deleteItemFromUser/{id}/{userId}")
    public void deleteCart(@PathVariable Long id,@PathVariable Long userId){
         this.controller.deleteItemFromUser(id,userId);
    }

    @CrossOrigin
    @GetMapping("findItemBySearchText/{searchText}")
    public List<ItemModel> findItem(@PathVariable String searchText){
       return this.controller.findItemBySearchText(searchText);
    }



    @CrossOrigin
    @DeleteMapping("deleteAccount/{id}")
    public void deleteUser(@PathVariable Long id){
        this.controller.deleteUser(id);
    }
    @CrossOrigin
    @GetMapping("addItemCount/{id}/{userId}")
    public void addCountToItem(@PathVariable Long id, @PathVariable Long userId){
        this.controller.addItemCart(id,userId);
    }

    @CrossOrigin
    @DeleteMapping("removeCountItem/{id}/{userId}")
    public void removeCountItem(@PathVariable Long id, @PathVariable Long userId){
        this.controller.deleteItemCount(id,userId);
    }

}
