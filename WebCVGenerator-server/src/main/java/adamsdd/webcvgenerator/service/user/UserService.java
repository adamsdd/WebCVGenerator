package adamsdd.webcvgenerator.service.user;

import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.dto.user.UserDto;
import adamsdd.webcvgenerator.repository.user.UserRepository;
import adamsdd.webcvgenerator.service.cv.CVDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final CVDataService cvDataService;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, CVDataService cvDataService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.cvDataService = cvDataService;
    }

    public UserDto login(User requestUser) {
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(requestUser.username, requestUser.password);
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            LOG.info("Login Successful, Username = " + user.getUsername());

            return getUserDtoByUsername(requestUser.username);
        } catch (BadCredentialsException ex) {
            LOG.error("Login Unsuccessful - Bad credentials");
            throw new BadCredentialsException("Bad credentials");
        }
    }

    public UserDto create(User user) {
        user.password = new BCryptPasswordEncoder().encode(user.password);
        UserDto savedUser = this.userRepository.save(user).dto();
        cvDataService.createCVData(savedUser.id);

        return savedUser;
    }

    public UserDetails loadUserByUsername(String username) {
        User user = findUserByUsername(username);

        UserBuilder userBuilder = withUsername(user.username);
        userBuilder.password(user.password);
        userBuilder.roles(user.role.name());

        return userBuilder.build();
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new EntityNotFoundException("Cannot find user = " + username);
        }

        return user;
    }

    public UserDto getUserDtoByUsername(String username) {
        return findUserByUsername(username).dto();
    }
}
