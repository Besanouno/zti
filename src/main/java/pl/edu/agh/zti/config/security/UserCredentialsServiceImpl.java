package pl.edu.agh.zti.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.agh.zti.dao.UserDao;
import pl.edu.agh.zti.exceptions.BadCredentialsException;
import pl.edu.agh.zti.model.User;

import javax.transaction.Transactional;

@Service
@Primary
public class UserCredentialsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserCredentialsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username)
                .orElseThrow(BadCredentialsException::new);
        return new UserCredentials(user);
    }
}