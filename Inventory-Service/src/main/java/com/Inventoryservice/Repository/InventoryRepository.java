package com.Inventoryservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Inventoryservice.Entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

	Optional<Inventory> findByProductId(Long productId);

}
