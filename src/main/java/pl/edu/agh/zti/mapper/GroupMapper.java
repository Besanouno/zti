package pl.edu.agh.zti.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.zti.dto.GroupDto;
import pl.edu.agh.zti.model.Group;

@Component
public class GroupMapper {

    private ModelMapper modelMapper;

    @Autowired
    public GroupMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Group dtoToEntity(GroupDto dto) {
        return modelMapper.map(dto, Group.class);
    }
}
