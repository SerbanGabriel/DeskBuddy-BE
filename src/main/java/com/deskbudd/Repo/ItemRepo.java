package com.deskbudd.Repo;

import com.deskbudd.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ItemRepo extends JpaRepository<ItemModel, Long> {

    List<ItemModel> findByTitleLike(String title);

    List<ItemModel> findByTitleContainingIgnoreCase(String title);


}