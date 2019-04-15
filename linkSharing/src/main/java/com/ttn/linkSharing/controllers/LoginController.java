package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.*;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.services.LoginService;
import com.ttn.linkSharing.services.SubscriptionService;
import com.ttn.linkSharing.services.TopicService;
import com.ttn.linkSharing.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    TopicService topicService;
    @Autowired
    SubscriptionService subscriptionService;


    @RequestMapping(value = "/loginButton")
    public String login(@ModelAttribute("loginCO") LoginCO loginCO, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = loginService.checkLoginPassword(loginCO);
        logger.debug("Checking Password");
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getActive()) {
                logger.debug("PASSWORD Matched");
                UserDTO userDTO = DTOToEntityViceVersaConvertor.EntityToDTO(optionalUser.get());
                logger.debug(userDTO.toString());
                HttpSession session = request.getSession();
                session.setAttribute("user", userDTO);
                logger.debug("userDTO.getUserName()----------" + userDTO.getUserName());
                return "redirect:/dashboard";
            }
            else
            {
                redirectAttributes.addFlashAttribute("inactiveMsg","Inactive User");

            }
        }
        redirectAttributes.addFlashAttribute("passWordMsg","Incorrect credentials! Try Again");

        return "redirect:/";
    }




/*
@RequestMapping(value = "/dashboard",method = RequestMethod.POST)
public  String dashboardredirect(@ModelAttribute() HttpServletRequest request){
        HttpSession session=request.getSession(false);
        session.getAttribute("userCO");
        return "redirect:/loginButton";
}*/


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpServletRequest request, Model model) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession.getAttribute("user") != null ) {
            System.out.println("------------------------------");
            logger.debug("--------------------Entered getSession");
            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
            model.addAttribute("topicList", topicService.topicCreatedBy(userDTO.getUserName()));
            logger.debug("-------------------------Got topic");
            model.addAttribute("Alltopic", subscriptionService.showAllSubscribedTopic(userDTO.getUserName()));
/*
            model.addAttribute("subscriptionList", subscriptionService.subscriptionById(userDTO.getId()));
*/

            logger.debug("Before Userco");
            model.addAttribute("userCO", userDTO);
            logger.debug("Before topicCO");
            model.addAttribute("topicCO", new TopicCO());
            logger.debug("before Subscription");
            model.addAttribute("subscriptionCO", new SubscriptionCO());
            logger.debug("before Linkresource");
            model.addAttribute("linkResourceCO", new LinkResourceCO());
            logger.debug("Before DocumentResource");
            model.addAttribute("AllTopicList",topicService.findAllTopic());
            model.addAttribute("userCO1",new UserCO());
            model.addAttribute("documentResourceCO", new DocumentResourceCO());

            return "dashboard";
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String dashboard(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") != null) {
            session.invalidate();
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
