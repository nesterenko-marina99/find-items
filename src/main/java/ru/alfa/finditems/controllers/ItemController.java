package ru.alfa.finditems.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfa.finditems.models.Item;

import java.util.List;

@RestController
public class ItemController {

    @PostMapping
    public Integer [] getItemsByContainedInAndColor () {
        
    }
}
