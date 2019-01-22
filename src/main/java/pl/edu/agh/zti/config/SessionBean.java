package pl.edu.agh.zti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.agh.zti.dao.UserDao;
import pl.edu.agh.zti.exceptions.NotFoundException;
import pl.edu.agh.zti.model.User;

@Component
public class SessionBean {

    private final UserDao userDao;

    @Autowired
    public SessionBean(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getCurrentUser() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userDao.findUserByUsername(principal)
                .orElseThrow(NotFoundException::new);
    }
}
