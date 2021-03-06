package pl.edu.uj.gotowanko.repositories;

import pl.edu.uj.gotowanko.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by alanhawrot on 22.03.15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    List<User> findAllByEmail(String email);

    User findByEmail(String email);
}
