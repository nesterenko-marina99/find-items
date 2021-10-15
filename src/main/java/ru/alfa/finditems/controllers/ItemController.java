package ru.alfa.finditems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alfa.finditems.models.Item;
import ru.alfa.finditems.services.ItemService;

@RequestMapping("/test")
@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseBody
    public Integer [] passContainedInAndColor (@RequestBody Item item) {
        return itemService.getItemIdsArray(item.getBox().getId(), item.getColor());
    }
}
