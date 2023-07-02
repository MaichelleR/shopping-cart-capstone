package com.sample.store.shoppingcart.controllers;

import com.sample.store.shoppingcart.Services.ShoppingCartService;
import com.sample.store.shoppingcart.exceptions.ShoppingCartNotFoundException;
import com.sample.store.shoppingcart.models.InventoryItem;
import com.sample.store.shoppingcart.models.ShoppingCart;
import com.sample.store.shoppingcart.models.ShoppingCartItem;
import com.sample.store.shoppingcart.repository.InventoryItemRepository;
import com.sample.store.shoppingcart.repository.ShoppingCartItemRepository;
import com.sample.store.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    InventoryItemRepository inventoryItemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @PostMapping("api/shopping-cart/{id}")
    public ResponseEntity<ShoppingCartResponse> addToCart(@PathVariable String id, @RequestBody ShoppingCartItemRequest shoppingCartItem){
        try {

            ShoppingCartResponse cart = shoppingCartService.addItemToCart(id,shoppingCartItem);
            return new ResponseEntity<>(cart, HttpStatus.OK);

        } catch (ShoppingCartNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/shopping-cart/{id}")
    public ResponseEntity<ShoppingCartResponse> getShoppingCart(@PathVariable String id) {
        try {
            //ShoppingCart cart = shoppingCartService.getCartByUsername(id);
            ShoppingCartResponse cart = shoppingCartService.getCartById(id);
            return new ResponseEntity<>(cart, HttpStatus.OK);

        } catch (ShoppingCartNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    IN PROGRESS
    @DeleteMapping("api/shopping-cart/{id}")
    public ResponseEntity<ShoppingCartResponse> deleteItemFromCart(@PathVariable String id, @RequestBody ShoppingCartItemRequest shoppingCartItem){
        try {
            ShoppingCartResponse cart = shoppingCartService.deleteItemFromCart(id,shoppingCartItem);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (ShoppingCartNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
     */
}
    // get a shopping by id
    // save a shopping cart by id
    // hello world


