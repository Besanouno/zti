package pl.edu.agh.zti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.zti.model.User;
import pl.edu.agh.zti.service.UserService;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(Long id);
}
