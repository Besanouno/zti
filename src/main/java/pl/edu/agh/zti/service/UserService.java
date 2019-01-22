package pl.edu.agh.zti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.zti.dao.RoleDao;
import pl.edu.agh.zti.dao.UserDao;
import pl.edu.agh.zti.dto.UserDto;
import pl.edu.agh.zti.exceptions.NotFoundException;
import pl.edu.agh.zti.mapper.UserMapper;
import pl.edu.agh.zti.model.Role;
import pl.edu.agh.zti.model.User;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Autowired
    public UserService(
            UserDao userDao,
            UserMapper userMapper,
            RoleService roleService) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    public void save(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        user.setRole(roleService.findRoleByName("USER"));
        userDao.save(user);
    }

    public void delete(Long id) {
        User user = userDao.findUserById(id).orElseThrow(NotFoundException::new);
        user.setActive(false);
    }
}
