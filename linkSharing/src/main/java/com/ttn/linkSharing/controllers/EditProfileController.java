package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.SubscriptionCO;
import com.ttn.linkSharing.CO.TopicCO;
import com.ttn.linkSharing.CO.UserCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.services.EditProfileService;
import com.ttn.linkSharing.services.SubscriptionService;
import com.ttn.linkSharing.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class EditProfileController {
    @Autowired
    TopicService topicService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    EditProfileService editProfileService;

    @RequestMapping("/Profile")
    public String editProfile(HttpServletRequest httpServletRequest, Model model) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
            model.addAttribute("userCO",userDTO);
            model.addAttribute("userCO1",new UserCO());
            model.addAttribute("topicList", topicService.topicCreatedBy(userDTO.getUserName()));
            model.addAttribute("Alltopic",subscriptionService.showAllSubscribedTopic(userDTO.getUserName()));
            model.addAttribute("topicCO", new TopicCO());
            model.addAttribute("subscriptionCO", new Subscription());
        }

        return "editProfile";

    }

    @RequestMapping("/editProfile")
    public String editProfileDetail(@ModelAttribute("userCO1") UserCO userCO,
                                    @ModelAttribute("topicCO") TopicCO topicCO,
                                    @ModelAttribute("subscriptionCO") SubscriptionCO subscriptionCO,
                                    HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                                    @RequestParam("photoPath")MultipartFile multipartFile)throws IOException {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
/*
            editProfileService.saveProfile(userDTO,topicCO,subscriptionCO);
*/
editProfileService.saveProfile(userCO,userDTO,multipartFile);



        }
        redirectAttributes.addFlashAttribute("successEdit","Profile Successfully Edited");
        return "redirect:/Profile";

    }



    @RequestMapping("/topic/{id}")
    public String deleteSubscription(@ModelAttribute("topicCO") TopicCO topicCO, @PathVariable("id") Integer topicId, HttpServletRequest httpServletRequest,RedirectAttributes redirectAttributes) {
        HttpSession session = httpServletRequest.getSession(false);

        if (session.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            topicService.deleteTopic(topicId);

        }
        return "redirect:/Profile";

    }


    @RequestMapping("/editPassword")
    public String editPassword(HttpServletRequest httpServletRequest,@ModelAttribute("userCO1") UserCO userCO,
                               @ModelAttribute("topicCO") TopicCO topicCO,
                               @ModelAttribute("subscriptionCO") SubscriptionCO subscriptionCO, RedirectAttributes redirectAttributes) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession.getAttribute("user") != null) {

            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
            editProfileService.savePassword(userCO,userDTO);

        }
        redirectAttributes.addFlashAttribute("successEditPassword","Password Successfully Changed");


        return "redirect:/Profile";

    }





}
