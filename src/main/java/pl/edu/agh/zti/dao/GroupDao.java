package pl.edu.agh.zti.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.zti.model.Group;
import pl.edu.agh.zti.model.User;
import pl.edu.agh.zti.projections.GroupViewProjection;

import java.util.Optional;

@Repository
public interface GroupDao extends PagingAndSortingRepository<Group, Long> {

    Optional<Group> findById(Long id);

    @Query("SELECT g FROM Group g WHERE g.id IN (SELECT ug.group.id FROM UserGroup ug WHERE ug.user=:user)")
    Page<Group> findAllNamesByUser(@Param("user") User user, Pageable pageable);

    @Query("SELECT g FROM Group g")
    Page<Group> findAllNames(Pageable pageable);
}
