package com.deskbudd.ControllerImpl;

import com.deskbudd.Repo.ItemRepo;
import com.deskbudd.Repo.UserRepo;
import com.deskbudd.models.ItemModel;
import com.deskbudd.models.UserLoginModel;
import com.deskbudd.models.UserModel;
import com.deskbudd.models.UserRegisterModelRequest;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerImpl {
    private final ItemRepo itemRepo;
    private final UserRepo userRepo;

    public ControllerImpl(ItemRepo itemRepo, UserRepo userRepo) {
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
    }

    public String searchForItemBy(String searchText) {
        return searchText;
    }

    public List<ItemModel> getAllElements(){
        return this.itemRepo.findAll();
    }

    public UserRegisterModelRequest saveUser(UserRegisterModelRequest newUser){
        UserModel userModel = new UserModel();
        userModel.setEmail(newUser.getEmail());
        userModel.setPassword(newUser.getPassword());
        userModel.setFirstName(newUser.getFirstName());
        userModel.setLastName(newUser.getLastName());

        this.userRepo.save(userModel);

        return newUser;
    }

    public UserLoginModel findUser(UserLoginModel userLoginModel){
        UserLoginModel userLoginModel1 = new UserLoginModel();

        UserModel user = this.userRepo.findByEmail(userLoginModel.getEmail());

        userLoginModel1.setEmail(user.getEmail());
        userLoginModel1.setPassword(user.getPassword());
        userLoginModel1.setFirstName(user.getFirstName());
        userLoginModel1.setImage(user.getPhoto());
        return userLoginModel1;
    }

    public void saveImage(byte[] iamge){
        UserModel user = this.userRepo.findByEmail("sgaby100@gmail.com");

        user.setPhoto(iamge);
        this.userRepo.save(user);
    }
}
