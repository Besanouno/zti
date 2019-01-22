package pl.edu.agh.zti.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.agh.zti.enums.UserGroupStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@Table(name = "user_groups")
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {

    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable=false, updatable=false)
    private Group group;

    private int points;

    @Enumerated(value = EnumType.STRING)
    private UserGroupStatus status;

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "user_id", nullable = false)
        private Long userId;

        @Column(name = "group_id", nullable = false)
        private Long groupId;
    }

}
