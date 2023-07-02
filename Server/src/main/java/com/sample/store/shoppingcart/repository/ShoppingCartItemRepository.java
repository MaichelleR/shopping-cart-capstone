package com.sample.store.shoppingcart.repository;

import com.sample.store.shoppingcart.models.InventoryItem;
import com.sample.store.shoppingcart.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {

}
