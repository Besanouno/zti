package pl.edu.agh.zti.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.agh.zti.dto.UserDto;
import pl.edu.agh.zti.model.User;

@Component
public class UserMapper {

    private ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User dtoToEntity(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        user.setPassword(encryptedPassword);
        return user;
    }
}
