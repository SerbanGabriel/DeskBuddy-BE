package com.deskbudd.ControllerImpl;

import com.deskbudd.Repo.ItemRepo;
import com.deskbudd.Repo.UserRepo;
import com.deskbudd.models.*;
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

    public void  delete(Long id){
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

    public void addToCart(Long itemId, Long userId){
        Optional<UserModel> user = this.userRepo.findById(userId);
        Optional<ItemModel> item = this.itemRepo.findById(itemId);

        for(ItemModel userItem : user.get().getItems()) {
            if(userItem.getId().equals(item.get().getId())){
                userItem.setCount(userItem.getCount() + 1);
                this.isItemPresent = true;
                break;
            }
        }
            if(checkIfItemExist(user.get().getItems(), itemId)){

                this.userRepo.save(user.get());
            }
            else{
                user.get().getItems().add(item.get());
                item.get().setCount(1);
                this.userRepo.save(user.get());
            }
    }

    private boolean checkIfItemExist(List<ItemModel> items, Long id){
        return items.stream().anyMatch(o -> o.getId().equals(id));
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

    public void addItemCart(Long id,Long userId){
        Optional<UserModel> user = this.userRepo.findById(userId);
        for(ItemModel model : user.get().getItems()){
            if(model.getId().equals(id)){
                    model.setCount(model.getCount()+1);
            }
        }
        this.userRepo.save(user.get());
    }

    public void deleteItemCount(Long id,Long userId){
        Optional<UserModel> user = this.userRepo.findById(userId);
        for(ItemModel model : user.get().getItems()){
            if(model.getId().equals(id)){
                model.setCount(model.getCount()-1);
                if(model.getCount() == 0){
                    user.get().getItems().remove(model);
                    break;
                }
            }
        }

        this.userRepo.save(user.get());
    }
    public UserLoginModel findUserEmail(String email){
        UserModel user = this.userRepo.findByEmail(email);

        UserLoginModel userLoginModel = new UserLoginModel();

        userLoginModel.setId(user.getId());
        userLoginModel.setItems(user.getItems());
        userLoginModel.setIsAdmin(user.getIsAdmin());
        userLoginModel.setImage(user.getPhoto());
        userLoginModel.setEmail(user.getEmail());
        userLoginModel.setFirstName(user.getFirstName());
        userLoginModel.setPassword(user.getPassword());
        return userLoginModel;
    }

    public UserLoginModel updateUsers(Long id, UserFormRequest userFormRequest){
        Optional<UserModel> user = this.userRepo.findById(id);
        UserLoginModel userLoginModel = new UserLoginModel();

        if(!userFormRequest.getEmail().isEmpty()){
            user.get().setEmail(userFormRequest.getEmail());
        }
        if(!userFormRequest.getFirstName().isEmpty()){
            user.get().setFirstName(userFormRequest.getFirstName());
        }
        if(!userFormRequest.getLastName().isEmpty()){
            user.get().setLastName(userFormRequest.getLastName());
        }
        if(!userFormRequest.getPassword().isEmpty()){
            user.get().setPassword(userFormRequest.getPassword());
        }

        userLoginModel.setFirstName(user.get().getFirstName());
        userLoginModel.setId(user.get().getId());
        userLoginModel.setEmail(user.get().getEmail());
        userLoginModel.setPassword(user.get().getPassword());
        userLoginModel.setItems(user.get().getItems());
        userLoginModel.setIsAdmin(user.get().getIsAdmin());
        userLoginModel.setImage(user.get().getPhoto());
        this.userRepo.save(user.get());
        return userLoginModel;
    }

    public List<ItemModel> findItemBySearchText(String searchText){
        return this.itemRepo.findByTitleContainingIgnoreCase(searchText);

    }
}
