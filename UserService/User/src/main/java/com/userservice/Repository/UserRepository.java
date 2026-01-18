package com.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.Entity.Users;


@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

	boolean existsByEmail(String email);

}
