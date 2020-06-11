package adamsdd.webcvgenerator;

import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.domain.user.UserRole;
import adamsdd.webcvgenerator.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TestDataPusher {

    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        userRepository.save(new User(null, "admin", "$2a$10$H9MHFX2Cqh1VWeGYhWcxVeJYcco6AmDGwg6S9RqSej7ECHQ6.O/na", UserRole.ADMIN));
        userRepository.save(new User(null, "user", "user", UserRole.USER));
//        String encoded = new BCryptPasswordEncoder().encode("admin");
//        System.out.println(encoded);
    }
}
