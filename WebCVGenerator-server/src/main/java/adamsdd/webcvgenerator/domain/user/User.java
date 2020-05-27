package adamsdd.webcvgenerator.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;

public class User {

    public String username;
    public String password;

    @JsonCreator
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
