package adamsdd.webcvgenerator.controller.user;

import adamsdd.webcvgenerator.domain.user.User;
import adamsdd.webcvgenerator.dto.user.UserDto;
import adamsdd.webcvgenerator.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDto login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping(value="/logout", method= RequestMethod.GET)
    public boolean logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return true;
        }

        return false;
    }


}
