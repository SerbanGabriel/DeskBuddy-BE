package com.deskbudd.Controller;

import com.deskbudd.ControllerImpl.ControllerImpl;
import com.deskbudd.models.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final ControllerImpl controller;

    @Autowired
    Controller(ControllerImpl controller) {
        this.controller = controller;
    }

    @GetMapping("search")
    String findElementBySearchText(@RequestParam String searchText){
        return this.controller.searchForItemBy(searchText);
    }

    @GetMapping("/all")
    List<ItemModel> findAllElements(){
        return controller.getAllElements();
    }
}
