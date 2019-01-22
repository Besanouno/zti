package pl.edu.agh.zti.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Date birthDate;
}
