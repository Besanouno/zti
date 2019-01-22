package pl.edu.agh.zti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.zti.dao.RoleDao;
import pl.edu.agh.zti.model.Role;

@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role findRoleByName(String name) {
        return roleDao
                .findRoleByName(name)
                .orElseThrow(RuntimeException::new);
    }
}
