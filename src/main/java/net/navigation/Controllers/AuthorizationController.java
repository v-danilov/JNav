package net.navigation.Controllers;

import net.navigation.ExtClasses.Circle;
import net.navigation.ExtClasses.FormData;
import net.navigation.Models.authorization.User;
import net.navigation.Models.navigation.Arc;
import net.navigation.Models.navigation.Node;
import net.navigation.Services.ArcService;
import net.navigation.Services.FloorService;
import net.navigation.Services.NodeService;
import net.navigation.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

@Controller
public class AuthorizationController {

    private UserService userService;


    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        modelMap.put("user", new User());
        return "login";
    }

    @RequestMapping(value = "login/auth", method = RequestMethod.POST)
    public ModelAndView authorization(@ModelAttribute("user") User user) {
        boolean b = userService.validateUser(user.getLogin(), user.getPass());
        System.out.println(b);
        if(b){

            return new ModelAndView(new RedirectView("/add", true));
        }
        else {
            return new ModelAndView(new RedirectView("/login", true));
        }
    }

}
