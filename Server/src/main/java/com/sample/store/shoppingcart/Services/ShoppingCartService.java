package com.sample.store.shoppingcart.Services;

import com.sample.store.shoppingcart.controllers.CartRow;
import com.sample.store.shoppingcart.controllers.ShoppingCartItemRequest;
import com.sample.store.shoppingcart.controllers.ShoppingCartResponse;
import com.sample.store.shoppingcart.exceptions.ShoppingCartNotFoundException;
import com.sample.store.shoppingcart.models.InventoryItem;
import com.sample.store.shoppingcart.models.ShoppingCart;
import com.sample.store.shoppingcart.models.ShoppingCartItem;
import com.sample.store.shoppingcart.repository.InventoryItemRepository;
import com.sample.store.shoppingcart.repository.ShoppingCartItemRepository;
import com.sample.store.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    InventoryItemRepository inventoryItemRepository;
    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    public ShoppingCartResponse getCartById(String id) {
        ShoppingCart cart = getShoppingCartFromDatabase(id);
        double finalPrice = 0;
        List<ShoppingCartItem> items = cart.getShoppingCartItems();
        List<CartRow> cartRowList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ShoppingCartItem item = items.get(i);
            String sku = item.getItem().getSku();
            String name = item.getItem().getName();
            int quantity = item.getQuantity();
            double price = item.getItem().getPrice() * quantity;
            CartRow cartRow = new CartRow(sku, name, quantity, String.format("%.2f",price));
            cartRowList.add(cartRow);
            finalPrice += price;
        }
        return new ShoppingCartResponse(cartRowList, String.valueOf(finalPrice));
    }

    public ShoppingCart getCartByUsername(String username) {
        Optional<ShoppingCart> optional = shoppingCartRepository.findById(username);
        if (optional.isEmpty()) {
            throw new ShoppingCartNotFoundException("Shopping Cart with" + username + "does not exist");
        }
        return optional.get();
    }

    public ShoppingCartResponse addItemToCart(String id, ShoppingCartItemRequest itemToAdd) {
        ShoppingCart cart = getShoppingCartFromDatabase(id);
        Optional<ShoppingCartItem> optional = getExistingItem(cart, itemToAdd);
        if (optional.isPresent()) {
            // UPDATE
            ShoppingCartItem existingItem = optional.get();
            existingItem.setQuantity(existingItem.getQuantity() + itemToAdd.getQuantity());
            shoppingCartItemRepository.save(existingItem);
        } else {
            // ADD
            InventoryItem item = inventoryItemRepository.findItemBySku(itemToAdd.getSku());
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(null, item, itemToAdd.getQuantity());
            shoppingCartItem = shoppingCartItemRepository.save(shoppingCartItem);
            cart.getShoppingCartItems().add(shoppingCartItem);
            shoppingCartRepository.save(cart);
        }
        return generateShoppingCart(cart);
    }

    private Optional<ShoppingCartItem> getExistingItem(ShoppingCart shoppingCart, ShoppingCartItemRequest shoppingCartItem) {
        for (ShoppingCartItem item : shoppingCart.getShoppingCartItems()) {
            if (item.getItem().getSku().equals(shoppingCartItem.getSku())) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    private ShoppingCart getShoppingCartFromDatabase(String id) {
        Optional<ShoppingCart> optional = shoppingCartRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ShoppingCartNotFoundException("Shopping Cart with" + id + "does not exist");
        }
        return optional.get();
    }

    private ShoppingCartResponse generateShoppingCart(ShoppingCart cart) {
        double finalPrice = 0;
        List<ShoppingCartItem> items = cart.getShoppingCartItems();
        List<CartRow> cartRowList = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ShoppingCartItem item = items.get(i);
            String sku = item.getItem().getSku();
            String name = item.getItem().getName();
            int quantity = item.getQuantity();
            double price = item.getItem().getPrice() * quantity;
            CartRow cartRow = new CartRow(sku, name, quantity, String.format("%.2f",price));
            cartRowList.add(cartRow);
            finalPrice += price;
        }
        return new ShoppingCartResponse(cartRowList, String.format("%.2f",finalPrice));
    }

    /*
    public ShoppingCartResponse deleteItemFromCart(String id, ShoppingCartItemRequest shoppingCartItem) {
        ShoppingCart cart = getShoppingCartFromDatabase(id);
        Optional<ShoppingCartItem> optional = getExistingItem(cart, shoppingCartItem);
        if (optional.get().getQuantity() > 1) {
                ShoppingCartItem existingItem = optional.get();
                existingItem.setQuantity(existingItem.getQuantity() - shoppingCartItem.getQuantity());
                shoppingCartItemRepository.saveAndFlush(existingItem);
        }else{
            ShoppingCartItem existingItem = optional.get();
            shoppingCartItemRepository.delete(existingItem);
        }
        return generateShoppingCart(cart);
    }
     */
}

