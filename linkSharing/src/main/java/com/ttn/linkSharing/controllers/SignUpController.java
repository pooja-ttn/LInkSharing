package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.*;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.*;
import com.ttn.linkSharing.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
public class SignUpController {
    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    DocumentResourceService documentResourceService;
    @Autowired
    LinkResourceService linkResourceService;
    ;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("userCO", new UserCO());
        modelAndView.addObject("loginCO", new LoginCO());
        return modelAndView;
    }

    @RequestMapping(value = "/registerButton", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("userCO") UserCO userCO,
                                 BindingResult bindingResult, @RequestParam("pic") MultipartFile file,
                                 HttpServletRequest httpServletRequest, Model model) throws IOException {
        HttpSession session = httpServletRequest.getSession(true);
        User user = COToEntityViceVersaConvertor.COconvertorToEntity(userCO);
        userService.saveUser(user, file);

        Optional<User> user1 = userService.findById(user);
        UserDTO userDTO = DTOToEntityViceVersaConvertor.EntityToDTO(user1.get());
        session.setAttribute("user", userDTO);
        model.addAttribute("topicList", topicService.topicCreatedBy(userDTO.getUserName()));
        model.addAttribute("Alltopic",subscriptionService.showAllSubscribedTopic(userDTO.getUserName()));
        model.addAttribute("userCO", (UserDTO)session.getAttribute("user"));
        model.addAttribute("loginCO", new LoginCO());
        model.addAttribute("topicCO", new TopicCO());
        model.addAttribute("subscriptionCO", new Subscription());
        model.addAttribute("linkResourceCO",new LinkResourceCO());
        model.addAttribute("documentResourceCO",new DocumentResourceCO());


        ModelAndView modelAndView = new ModelAndView("dashboard");
        return modelAndView;

}

    /*

        @RequestMapping(value = "/verifyregister")
        public String register(@ModelAttribute User user){
            userService.registerUser(user.get)


        }
    */
    @GetMapping("/checkUsernameAvailability")
    @ResponseBody
    public String checkUserName(@RequestParam(value = "uname") String userName, @RequestParam("email") String email) {
        Boolean flag = userService.findByUserNameOrEmail(userName, email);
        if (flag) {
            return "true";
        }

        return "false";


    }
}


