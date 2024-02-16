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

    public void  delete(Integer id){
        this.itemRepo.deleteById(id);
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
        userLoginModel1.setIsAdmin(user.getIsAdmin());
        userLoginModel1.setId(user.getId());
        return userLoginModel1;
    }

    public void saveImage(byte[] image,String email) {
        UserModel user = this.userRepo.findByEmail(email);

        user.setPhoto(image);
        this.userRepo.save(user);
    }

    public void saveItem(ItemModel item){
        itemRepo.save(item);
    }

    public void addToCart(Integer itemId, Long userId){
        Optional<UserModel> user = this.userRepo.findById(userId);
        Optional<ItemModel> item = this.itemRepo.findById(itemId);
        user.get().getItems().add(item.get());

        this.userRepo.save(user.get());
    }
}
