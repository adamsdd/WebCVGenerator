package adamsdd.webcvgenerator.repository.user;

import adamsdd.webcvgenerator.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
