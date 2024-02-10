package com.deskbudd.ControllerImpl;

import com.deskbudd.Repo.ItemRepo;
import com.deskbudd.models.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ControllerImpl {
    private final ItemRepo itemRepo;

    public ControllerImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public String searchForItemBy(String searchText) {
        return searchText;
    }

    public List<ItemModel> getAllElements(){
        return this.itemRepo.findAll();
    }
}
