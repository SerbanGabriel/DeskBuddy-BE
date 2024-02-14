package com.deskbudd.Repo;

import com.deskbudd.models.ItemModel;
import com.deskbudd.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

    public UserModel findByEmail(String email);
}