package com.deskbudd.ControllerImpl;

import com.deskbudd.Repo.ItemRepo;
import com.deskbudd.Repo.UserRepo;
import com.deskbudd.models.ItemModel;
import com.deskbudd.models.UserLoginModel;
import com.deskbudd.models.UserModel;
import com.deskbudd.models.UserRegisterModelRequest;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerImpl {
    private final ItemRepo itemRepo;
    private final UserRepo userRepo;

    private Boolean isItemPresent = false;

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

        UserModel model = this.userRepo.save(userModel);
        newUser.setId(model.getId());

        return newUser;
    }

    public UserLoginModel findUserById(Long id){
        UserLoginModel userLoginModel1 = new UserLoginModel();

        Optional<UserModel> user = this.userRepo.findById(id);

        return getUserLoginModel(userLoginModel1, user.get());
    }

    private UserLoginModel getUserLoginModel(UserLoginModel userLoginModel1, UserModel user) {
        userLoginModel1.setEmail(user.getEmail());
        userLoginModel1.setPassword(user.getPassword());
        userLoginModel1.setFirstName(user.getFirstName());
        userLoginModel1.setImage(user.getPhoto());
        userLoginModel1.setIsAdmin(user.getIsAdmin());
        userLoginModel1.setId(user.getId());
        userLoginModel1.setItems(user.getItems());
        return userLoginModel1;
    }

    public void saveImage(byte[] image,Long id) {
        Optional<UserModel> user = this.userRepo.findById(id);

        user.get().setPhoto(image);
        this.userRepo.save(user.get());
    }

    public void saveItem(ItemModel item){
        itemRepo.save(item);
    }

    public void addToCart(Integer itemId, Long userId){
        Optional<UserModel> user = this.userRepo.findById(userId);
        Optional<ItemModel> item = this.itemRepo.findById(itemId);

        for(ItemModel userItem : user.get().getItems()) {
            if(userItem.getId().equals(item.get().getId())){
                userItem.setCount(userItem.getCount() + 1);
                this.isItemPresent = true;
                return;
            }
        }
        if(this.isItemPresent){
            this.userRepo.save(user.get());
        }else{
            user.get().getItems().add(item.get());
            this.userRepo.save(user.get());
        }
    }

    public void deleteItemFromUser(Long id, Long userId){
        Optional<UserModel> user = this.userRepo.findById(userId);
        for(ItemModel item : user.get().getItems()){
            if(item.getId().equals(id)){
                user.get().getItems().remove(item);
                this.userRepo.save(user.get());
                return;
            }
        }
    }
    public void deleteUser(Long id){
        this.userRepo.deleteById(id);
    }


    public UserLoginModel findUserEmail(String email){
        UserModel user = this.userRepo.findByEmail(email);

        UserLoginModel userLoginModel = new UserLoginModel();

        userLoginModel.setId(user.getId());
        userLoginModel.setItems(user.getItems());
        userLoginModel.setImage(user.getPhoto());
        userLoginModel.setEmail(user.getEmail());
        userLoginModel.setPassword(user.getPassword());
        return userLoginModel;
    }
}
