package pl.edu.agh.zti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.zti.dto.GroupDto;
import pl.edu.agh.zti.service.GroupService;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void save(@Valid @RequestBody GroupDto dto) {
        groupService.save(dto);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<String> getAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return groupService.getAllGroups(page, size);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<String> get(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return groupService.getGroupsByUser(page, size);
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long groupId) {
        groupService.delete(groupId);
    }
}
