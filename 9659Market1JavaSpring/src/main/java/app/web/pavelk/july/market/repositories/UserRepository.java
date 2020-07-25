package app.web.pavelk.july.market.repositories;


import app.web.pavelk.july.market.entities.User;
import app.web.pavelk.july.market.entities.dtos.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    <T> T findByUsername(String username, Class<T> type);

//    <T> T findByLastName(String lastName, Class<T> type);
//    @Query("SELECT new de.smarterco.example.dto.UserNameDTO(u.id, u.name) FROM User u WHERE u.name = :name")
//    List<UserNameDTO> retrieveUsernameAsDTO(@Param("name") String name);

    List<UserDto> findAllBy();

    @Secured("ROLE_ADMIN")
//уровень методов защта
    Optional<User> findById(Long id);
}
