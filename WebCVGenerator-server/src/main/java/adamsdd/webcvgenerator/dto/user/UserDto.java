package adamsdd.webcvgenerator.dto.user;

import adamsdd.webcvgenerator.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonCreator;

public class UserDto {

    public final Long id;
    public final String username;
    public final UserRole role;

    @JsonCreator
    public UserDto(Long id, String username, UserRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
