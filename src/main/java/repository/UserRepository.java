package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import jpa.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailId(String email);
}
