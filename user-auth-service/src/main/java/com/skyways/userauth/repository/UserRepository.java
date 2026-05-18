package com.skyways.userauth.repository;
 
import com.skyways.userauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
 
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    Optional<User> findByUsername(String username);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}
 