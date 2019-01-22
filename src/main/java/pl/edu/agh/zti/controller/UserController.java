package pl.edu.agh.zti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.zti.service.UserService;
import pl.edu.agh.zti.dto.UserDto;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody final UserDto dto) {
        userService.save(dto);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable final Long id) {
        userService.delete(id);
    }
}
