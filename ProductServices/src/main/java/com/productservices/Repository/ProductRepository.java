package com.productservices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productservices.Entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long>{

	boolean existsByName(String name);

}
