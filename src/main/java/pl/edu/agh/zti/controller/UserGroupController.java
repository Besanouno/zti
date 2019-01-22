package pl.edu.agh.zti.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.zti.service.UserGroupService;

@RestController
@RequestMapping("/groups/{groupId}")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @Autowired
    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping("/users/{userId}/invitation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void invite(@PathVariable Long groupId, @PathVariable Long userId) {
        userGroupService.invite(groupId, userId);
    }

    @PostMapping("/invitation/accept")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void acceptInvitation(@PathVariable Long groupId) {
        userGroupService.acceptInvitation(groupId);
    }

    @PostMapping("/invitation/reject")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectInvitation(@PathVariable Long groupId) {
        userGroupService.rejectInvitation(groupId);
    }

    @PostMapping("/leave")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void leave(@PathVariable Long groupId) {
        userGroupService.leave(groupId);
    }
}
