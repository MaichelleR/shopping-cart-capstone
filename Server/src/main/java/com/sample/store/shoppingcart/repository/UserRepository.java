package com.sample.store.shoppingcart.repository;

import com.sample.store.shoppingcart.models.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<StoreUser, Integer> {


}
