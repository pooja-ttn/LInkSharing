package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.TopicCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping("/edit/{subscriptionId}/{newName}")
    public String editSubscription(@ModelAttribute("topicCO") TopicCO topicCO, @PathVariable("subscriptionId") Integer subscriptionId, @PathVariable("newName") String topic, HttpServletRequest httpServletRequest, Model model) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) session.getAttribute("user");

        }
        return "redirect:/dashboard";
    }


    @RequestMapping("/delete/{subscriptionId}")
    public String deleteSubscription(@ModelAttribute("topicCO") TopicCO topicCO, @PathVariable("subscriptionId") Integer subscriptionId, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);

        if (session.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) session.getAttribute("user");

            subscriptionService.deleteSubscriptionId(subscriptionId, userDTO);
        }
        return "redirect:/dashboard";

    }
/*
    @RequestMapping("/read/{subscribedId}")
    public String isRead(@PathVariable("subscriptionId") Integer subscriptionId, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session.getAttribute("user") != null) {
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            subscriptionService.updateSubscription(subscriptionId, userDTO.getUserName());
        }
        return "redirect:/dashboard";
    }*/
}