package adamsdd.webcvgenerator.provider.user;

import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.domain.user.UserRole;

public class UserProvider {

    public static User user() {
        return new User(1L, "Jan", "haslo", UserRole.ADMIN);
    }
}
