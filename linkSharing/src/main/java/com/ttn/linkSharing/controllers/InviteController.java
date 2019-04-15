package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class InviteController {

    @Autowired
    EmailService emailService;

    @PostMapping("/invite")
    public String sendEmailInvite(@RequestParam("invitation-Email") String email, @RequestParam(value = "invite-topic-name") String topic,
                                  RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) throws MessagingException {

        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession.getAttribute("user") != null) {
            UserDTO user = (UserDTO) httpSession.getAttribute("user");
            emailService.sendInvite(email, topic, redirectAttributes,user);
        }
        /*redirectAttributes.addAttribute("check","true");
        redirectAttributes.addAttribute("messsage","Mail has been sent successufully.....");*/
        return "redirect:/dashboard";

    }
}

