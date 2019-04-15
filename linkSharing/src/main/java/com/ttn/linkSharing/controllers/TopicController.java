package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.DocumentResourceCO;
import com.ttn.linkSharing.CO.LinkResourceCO;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
 public class TopicController {
    @Autowired
    TopicService topicService;


    @Autowired
    SubscriptionService subscriptionService;


    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/createTopic",method = RequestMethod.POST)
    public String topic1(@ModelAttribute("topicCO") TopicCO topicCO, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        logger.debug("TopicCO ----------" + topicCO.getTopicName());
        HttpSession session = httpServletRequest.getSession(false);
        logger.debug("session-----", session);

        if (session.getAttribute("user") != null) {
            logger.debug("getAttribute of session ", session.getAttribute("user"));
            UserDTO userDTO= (UserDTO) session.getAttribute("user");
            topicService.saveTopic(topicCO, userDTO);

        }
        redirectAttributes.addFlashAttribute("TopicCreated","Topic created Successfully!!!!");
            return "redirect:/dashboard";

    }


    @RequestMapping("/topics/{topicId}")
    public ModelAndView viewTopic(@PathVariable("topicId") Integer topicId, HttpServletRequest httpServletRequest, Model model) {
ModelAndView modelAndView=new ModelAndView("ViewTopic");
        HttpSession session = httpServletRequest.getSession(false);
        if (session.getAttribute("user") != null) {
            Optional<Topic> topic = topicService.findById(topicId);
            UserDTO userDTO=(UserDTO)session.getAttribute("user");
             modelAndView.addObject("userCO",userDTO);
                modelAndView.addObject("topic", topic.get());
            model.addAttribute("documentResourceCO", new DocumentResourceCO());
            model.addAttribute("linkResourceCO",new LinkResourceCO());
            model.addAttribute("subscription",subscriptionService.findByUser_UserNameAndTopic_Name(userDTO.getUserName(),topic.get().getName()));
            model.addAttribute("Alltopic",subscriptionService.showAllSubscribedTopic(userDTO.getUserName()));
            model.addAttribute("topicList", topicService.topicCreatedBy(userDTO.getUserName()));

        }

  return modelAndView;
    }

    @RequestMapping(value = "/editTopic",method = RequestMethod.POST)
    public String editTopic(@RequestParam(value = "newtopic")String newTopic,
                            @RequestParam(value = "id")Integer id,
                            Model model,
                            HttpSession httpSession) {

        return topicService.editTopic(newTopic,id,(UserDTO)httpSession.getAttribute("user"));
    }


    @PostMapping("/unsubscribe")
    @ResponseBody
    public String unsubscribe(@RequestParam ("topicId")Integer topicId,HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session.getAttribute("user") != null) {

            UserDTO userDTO = (UserDTO) session.getAttribute("user");

            subscriptionService.deleteSubscriptionWhereTopicIdAndUserId(topicId, userDTO);
        }
        return "true";

    }


    @PostMapping("/subscribe")
    @ResponseBody
    public String subscribe(@RequestParam ("topicId")Integer topicId,HttpServletRequest httpServletRequest,RedirectAttributes redirectAttributes) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session.getAttribute("user") != null) {

            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if(subscriptionService.countByTopic_IdAndAndUser_UserName(topicId,userDTO.getUserName())==0){
                subscriptionService.saveSubscriptionForTopicAndUser(topicId, userDTO);
            }
            else
            {
               return "false";
            }
        }
        return "true";

    }


}




