package app.web.pavelk.july.market.repositories;


import app.web.pavelk.july.market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Secured("ROLE_ADMIN")
//уровень методов защта
    Optional<User> findById(Long id);
}
