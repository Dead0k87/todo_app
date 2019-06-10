package com.example.springbootfirstwebapplication.controller;


import com.example.springbootfirstwebapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Security;

@Controller
//@SessionAttributes("name")
public class LogoutController {
    @Autowired
    LoginService service; // Spring can use Dependency Injection (autowired). When Spring service will find any instance of Autowired LoginService then take it and bring here

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    // anything with slash URL will send user to welcome page
    public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        //model.put("name", getLoggedInUserName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) { // athentication.isAuthenticated
//            authentication.setAuthenticated(false);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, response, authentication);
        }
        return "redirect:/"; //  home page
    }

    private String getLoggedInUserName() {
//get login(userID) from Spring Security

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
}
//    @RequestMapping(value = "/login", method = RequestMethod.POST) // скроет параметры
//    public String loginMethod(@RequestParam String name, @RequestParam String password, ModelMap model) {
//        boolean isValidUser = service.validateUser(name, password); // if valid then welcome page
//        if (!isValidUser) {
//            model.put("errorMessage", "Invalid login/pass");
//            return "login"; // will go to login.jsp
//        } else {
//            model.put("name", "in28Minutes");// вводя выше в @RequestParam name мы передаем его в модель, где дальше он заносится в сессию
//            model.put("password", password);//putting in the model
//            return "welcome"; // welcome.jsp
//        }
//    }


