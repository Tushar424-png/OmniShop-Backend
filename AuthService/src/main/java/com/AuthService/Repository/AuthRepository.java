package com.AuthService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AuthService.Entity.UserAuth;

@Repository
public interface AuthRepository extends JpaRepository<UserAuth,Long>{

	Optional<UserAuth> findByEmail(String email);

}
