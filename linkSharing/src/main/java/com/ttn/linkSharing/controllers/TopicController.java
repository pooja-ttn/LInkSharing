package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.TopicCO;
import com.ttn.linkSharing.DTO.TopicDTO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Visibility;
import com.ttn.linkSharing.repositories.TopicRepository;
import com.ttn.linkSharing.services.SubscriptionService;
import com.ttn.linkSharing.services.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
 public class TopicController {
    @Autowired
    TopicService topicService;


    @Autowired
    SubscriptionService subscriptionService;


    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/createTopic",method = RequestMethod.POST)
    public String topic1(@ModelAttribute("topicCO") TopicCO topicCO, HttpServletRequest httpServletRequest) {
        logger.debug("TopicCO ----------" + topicCO.getTopicName());
        HttpSession session = httpServletRequest.getSession(false);
        logger.debug("session-----", session);

        if (session.getAttribute("user") != null) {
            logger.debug("getAttribute of session ", session.getAttribute("user"));
            UserDTO userDTO= (UserDTO) session.getAttribute("user");
            topicService.saveTopic(topicCO, userDTO);

        }
            return "redirect:/dashboard";

    }

}




