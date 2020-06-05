package adamsdd.webcvgenerator.controller.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {


//    @RequestMapping("/login")
//    public boolean login(@RequestBody User user) {
//        return "admin".equals(user.username) && "admin".equals(user.password);
//    }

//    @RequestMapping("/user")
//    public Principal user(HttpServletRequest request) {
//        String authToken = request.getHeader("Authorization")
//                .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//                .decode(authToken)).split(":")[0];
//    }
}
