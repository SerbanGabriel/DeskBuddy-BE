package com.deskbudd.Repo;

import com.deskbudd.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ItemRepo extends JpaRepository<ItemModel, Integer> {

}