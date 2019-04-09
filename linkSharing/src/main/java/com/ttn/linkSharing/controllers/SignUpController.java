package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.LoginCO;
import com.ttn.linkSharing.CO.UserCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Role;
import com.ttn.linkSharing.services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class SignUpController {
    @Autowired
    UserSignUpService userService;
    ;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("userCO", new UserCO());
        modelAndView.addObject("loginCO",new LoginCO());
        return modelAndView;
    }

    @RequestMapping(value = "/registerButton", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("userCO") UserCO userCO, BindingResult bindingResult, @RequestParam("pic") MultipartFile file) throws IOException {
        User user = COToEntityViceVersaConvertor.COconvertorToEntity(userCO);
        user.setRole(Role.USER);
        System.out.println(userCO.getPassword());
        userService.saveUser(user, file);
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
    public String checkUserName(@RequestParam(value = "uname") String userName,@RequestParam("email")String email) {
        Boolean flag=userService.findByUserNameOrEmail(userName,email);
        if(flag){
            return "true";
        }

        return "false";


    }
}


