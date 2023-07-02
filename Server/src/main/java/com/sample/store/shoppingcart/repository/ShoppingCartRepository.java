package com.sample.store.shoppingcart.repository;

import com.sample.store.shoppingcart.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,String> {

}
