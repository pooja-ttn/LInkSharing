package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.LoginCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.services.LoginService;
import com.ttn.linkSharing.services.UserSignUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserSignUpService userService;
    @Autowired
    LoginService loginService;
    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/loginButton", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginCO") LoginCO loginCO, HttpServletRequest request, Model model) {
        Optional<User> optionalUser = loginService.checkLoginPassword(loginCO);
        if (optionalUser.isPresent()) {
                UserDTO userDTO = DTOToEntityViceVersaConvertor.EntityToDTO(optionalUser.get());
                HttpSession session = request.getSession();
                session.setAttribute("user", userDTO);
                return "redirect:/dashboard";
            }
        return "redirect:/";
    }


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpServletRequest request, Model model) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("userCO",httpSession.getAttribute("user"));
            return "dashboard";
        }
        return "redirect:/";
    }


/*
    @RequestMapping(value = "/loginButton", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") UserCO userCO, BindingResult bindingResult, Model model, HttpSession httpSession) throws IOException {
        if(bindingResult.hasErrors()) {
            return "index";
        }

        User user = COToEntityConvertor.COconvertorToEntity(userCO);
        Boolean flag = loginService.checkLoginPassword(user);
        if (flag == true) {
            return"dashboard";
        } else {
            model.addAttribute("error", "UserName & Password mismatch");
            return "redirect:/";
        }

    }*/
}
