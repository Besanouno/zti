package pl.edu.agh.zti.projections;

import org.springframework.data.rest.core.config.Projection;
import pl.edu.agh.zti.model.Group;

@Projection(name = "groupViewProjection", types = {Group.class})
public interface GroupViewProjection {
    String getName();
}
