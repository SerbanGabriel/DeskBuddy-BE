package com.deskbudd.Repo;

import com.deskbudd.models.ItemModel;
import com.deskbudd.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    public UserModel findByEmail(String name);
}