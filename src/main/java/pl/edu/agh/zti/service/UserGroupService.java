package pl.edu.agh.zti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.zti.config.SessionBean;
import pl.edu.agh.zti.dao.UserGroupDao;
import pl.edu.agh.zti.enums.UserGroupStatus;
import pl.edu.agh.zti.exceptions.IllegalUserGroupStatus;
import pl.edu.agh.zti.model.User;
import pl.edu.agh.zti.model.UserGroup;

import java.util.Optional;

@Service
public class UserGroupService {

    private final UserGroupDao userGroupDao;
    private final SessionBean sessionBean;

    @Autowired
    public UserGroupService(
            UserGroupDao userGroupDao,
            SessionBean sessionBean) {
        this.userGroupDao = userGroupDao;
        this.sessionBean = sessionBean;
    }

    public void invite(Long groupId, Long userId) {
        UserGroup.Id userGroupId = new UserGroup.Id(userId, groupId);
        Optional<UserGroup> userGroupOpt = userGroupDao.findById(userGroupId);

        if (userGroupOpt.isPresent()) {
            UserGroup userGroup = userGroupOpt.get();
            if (canBeInvited(userGroup)) {
                userGroup.setStatus(UserGroupStatus.INVITED);
            } else {
                throw new IllegalUserGroupStatus();
            }
        } else {
            UserGroup userGroup = UserGroup.builder()
                    .status(UserGroupStatus.INVITED)
                    .id(userGroupId)
                    .build();
            userGroupDao.save(userGroup);
        }
    }

    private boolean canBeInvited(UserGroup ug) {
        return  UserGroupStatus.LEAVED.equals(ug.getStatus()) || UserGroupStatus.REJECTED.equals(ug.getStatus());
    }

    public void acceptInvitation(Long groupId) {
        changeStatus(groupId, UserGroupStatus.INVITED, UserGroupStatus.PARTICIPANT);
    }

    public void rejectInvitation(Long groupId) {
        changeStatus(groupId, UserGroupStatus.INVITED, UserGroupStatus.REJECTED);
    }

    public void leave(Long groupId) {
        changeStatus(groupId, UserGroupStatus.PARTICIPANT, UserGroupStatus.LEAVED);
    }

    private void changeStatus(Long groupId, UserGroupStatus requiredInitialStatus, UserGroupStatus newStatus) {
        User currentUser = sessionBean.getCurrentUser();
        UserGroup.Id userGroupId = new UserGroup.Id(currentUser.getId(), groupId);
        UserGroup userGroup = userGroupDao.findById(userGroupId)
                .filter(ug -> ug.getStatus() == requiredInitialStatus)
                .orElseThrow(IllegalUserGroupStatus::new);
        userGroup.setStatus(newStatus);
        userGroupDao.save(userGroup);
    }
}
