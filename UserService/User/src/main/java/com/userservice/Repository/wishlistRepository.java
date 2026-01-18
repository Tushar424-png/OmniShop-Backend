package com.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.Entity.WishlistItem;

@Repository
public interface wishlistRepository extends JpaRepository<WishlistItem,Long> {

}
