package com.sample.store.shoppingcart.controllers;

import com.sample.store.shoppingcart.exceptions.ItemNotFoundException;
import com.sample.store.shoppingcart.models.InventoryItem;
import com.sample.store.shoppingcart.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class InventoryItemController {

    @Autowired
    InventoryItemRepository inventoryItemRepository;
    @GetMapping("items")
   List<InventoryItem> getAllItemsCurrentlyInCart(){
      return inventoryItemRepository.findAll();
    }

    @GetMapping("items/sku/{sku}")
    InventoryItem getItemBySku(@PathVariable String sku){
        Optional<InventoryItem> optional = inventoryItemRepository.findById(sku);
        if(!optional.isPresent()){
            throw new ItemNotFoundException("Item with id " + sku + "does not exist");
        }return optional.get();
        //return inventoryItemRepository.findItemBySku(sku);
    }
}
