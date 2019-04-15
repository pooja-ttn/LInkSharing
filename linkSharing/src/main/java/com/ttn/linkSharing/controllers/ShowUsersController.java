package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.UserCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ShowUsersController {
    @Autowired
    UserService userService;


    @RequestMapping("/UserDetail")
    public ModelAndView showUsers(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        ModelAndView modelAndView = new ModelAndView("admin");

        if (session.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (userDTO.getUserName().equals("admin")) {
                modelAndView.addObject("userCO",userService.findAll());
            } else {
                ModelAndView modelAndView1 = new ModelAndView("dashboard");

            }


        }
        return modelAndView;

    }



    @RequestMapping("/activation/{id}")
    public String viewPage(HttpServletRequest httpServletRequest, @PathVariable("id") Integer id){
        HttpSession session = httpServletRequest.getSession(false);
        if (session.getAttribute("user") != null) {

            userService.ActiveByUserId(id);
        }
        return "redirect:/UserDetail";
    }
}

