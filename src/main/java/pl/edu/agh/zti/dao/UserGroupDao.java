package pl.edu.agh.zti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.zti.model.User;
import pl.edu.agh.zti.model.UserGroup;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGroupDao extends JpaRepository<UserGroup, UserGroup.Id> {
    Optional<UserGroup> findById(UserGroup.Id id);
}
