package adamsdd.webcvgenerator.domain.user;

import adamsdd.webcvgenerator.dto.user.UserDto;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @Column(nullable = false, unique = true)
    public String username;
    public String password;
    @Enumerated(EnumType.STRING)
    public UserRole role;

    public User() {
    }

    public User(Long id, String username, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDto dto() {
        return new UserDto(id, username, role);
    }
}
