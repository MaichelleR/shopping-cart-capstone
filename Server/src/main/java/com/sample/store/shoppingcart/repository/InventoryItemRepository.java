package com.sample.store.shoppingcart.repository;

import com.sample.store.shoppingcart.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, String> {
    InventoryItem findItemBySku(String name);
}
