package net.navigation.Controllers;

import net.navigation.ExtClasses.FormData;
import net.navigation.Models.authorization.User;
import net.navigation.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorizationController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setArcService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loin(ModelMap modelMap){
        modelMap.put("user", new User());
        return "login";
    }

    @RequestMapping(value = "login/auth", method = RequestMethod.POST)
    public String authorization(@ModelAttribute("user") User user){
        boolean b = userService.validateUser(user.getLogin(),user.getPass());
        System.out.println(b);
        return "login";
    }

}
