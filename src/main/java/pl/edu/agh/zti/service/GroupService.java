package pl.edu.agh.zti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.agh.zti.config.SessionBean;
import pl.edu.agh.zti.dao.GroupDao;
import pl.edu.agh.zti.dao.UserGroupDao;
import pl.edu.agh.zti.dto.GroupDto;
import pl.edu.agh.zti.enums.UserGroupStatus;
import pl.edu.agh.zti.exceptions.NotFoundException;
import pl.edu.agh.zti.mapper.GroupMapper;
import pl.edu.agh.zti.model.Group;
import pl.edu.agh.zti.model.User;
import pl.edu.agh.zti.model.UserGroup;
import pl.edu.agh.zti.projections.GroupViewProjection;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {

    private final GroupDao groupDao;
    private final GroupMapper groupMapper;
    private final UserGroupDao userGroupDao;
    private final SessionBean sessionBean;

    @Autowired
    public GroupService(
            GroupDao groupDao,
            GroupMapper groupMapper,
            UserGroupDao userGroupDao,
            SessionBean sessionBean) {
        this.groupDao = groupDao;
        this.groupMapper = groupMapper;
        this.sessionBean = sessionBean;
        this.userGroupDao = userGroupDao;
    }

    public void save(GroupDto dto) {
        Group group = groupMapper.dtoToEntity(dto);
        group = groupDao.save(group);
        addAdminToGroup(group);
    }

    private void addAdminToGroup(Group group) {
        User admin = sessionBean.getCurrentUser();
        UserGroup.Id userGroupId = new UserGroup.Id(admin.getId(), group.getId());
        UserGroup userGroup = UserGroup.builder()
                .id(userGroupId)
                .status(UserGroupStatus.ADMIN)
                .build();
        userGroupDao.save(userGroup);
    }

    public void delete(Long id) {
        Group group = groupDao.findById(id).orElseThrow(NotFoundException::new);
        group.setActive(false);
    }

    public Page<String> getAllGroups(int page, int size) {
        return groupDao.findAllNames(new PageRequest(page, size))
                .map(Group::getName);
    }

    public Page<String> getGroupsByUser(int page, int size) {
        User user = sessionBean.getCurrentUser();
        return groupDao.findAllNamesByUser(user, new PageRequest(page, size))
                .map(Group::getName);
    }

}
