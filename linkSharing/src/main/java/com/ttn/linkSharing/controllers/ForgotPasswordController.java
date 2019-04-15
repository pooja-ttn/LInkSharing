package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.Utils.PassWordUtils;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.services.EmailService;
import com.ttn.linkSharing.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ForgotPasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
    @Autowired
    PassWordUtils passWordUtils;
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    /*

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;
    */



    // Display forgotPassword page
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail,HttpServletRequest request) {
            // Lookup user in database by e-mail
            Optional<User> optional = userService.findUserByEmail(userEmail);

            if (!optional.isPresent()) {

                modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
            } else {

                // Generate random 36-character string token for reset password

                User user = optional.get();
                user.setResetToken(UUID.randomUUID().toString());

                // Save token to database
                userService.save(user);

                String appUrl = "localhost:8080";

                // Email message
                SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
                passwordResetEmail.setFrom("123linkShare123@gmail.com");
                passwordResetEmail.setTo(user.getEmail());
                passwordResetEmail.setSubject("Password Reset Request");
                passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                        + "/reset?token=" + user.getResetToken());
                emailService.send(passwordResetEmail);

                // Add success message to view
                modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
            }
        modelAndView.setViewName("forgotPassword");
        return modelAndView;

    }

    // Display form to reset password
    @GetMapping(value = "/reset")
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        Optional<User> user = userService.findUserByResetToken(token);

        if (!user.isPresent()) { // Token found in DB
            modelAndView.addObject("errorMessage1", "Oops!  This is an invalid password reset link.");
        }
        modelAndView.addObject("resetToken",token);
        modelAndView.setViewName("fragments/changePassword");
        return modelAndView;
    }

    // Process reset password form
   @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView,@RequestParam("token") String token,@RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        // Find the user associated with the reset token
       logger.debug(token);
        Optional<User> user = userService.findUserByResetToken(token);

        // This should always be non-null but we check just in case
        if (user.isPresent()) {

            User resetUser = user.get();

            // Set new password
            resetUser.setPassword(PassWordUtils.encrypt(requestParams.get("confirmPassword")));

            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);

            // Save user
            userService.save(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage1", "You have successfully reset your password.  You may now login.");
            modelAndView.setViewName("redirect:/");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("fragments/changePassword");
        }

        return modelAndView;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:/");
    }

}
