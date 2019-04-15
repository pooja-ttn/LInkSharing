package com.ttn.linkSharing.services;

import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.Utils.EmailUtils;
import com.ttn.linkSharing.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    EmailUtils emailUtil;

    public void sendInvite(String email, String relatedTopic, RedirectAttributes redirectAttributes, UserDTO user) throws MessagingException {

        SimpleMailMessage sendInviteEmail = new SimpleMailMessage();
        sendInviteEmail.setTo(email);
        sendInviteEmail.setSubject("Invitation Link");
        sendInviteEmail.setText(user.getFirstName() + " " + user.getLastName() +
                " has invited you to subscribe the topic " + relatedTopic);
        emailUtil.sendEmail(sendInviteEmail);
        redirectAttributes.addFlashAttribute("message", "A link to invite has been sent to " + email);
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

    }

    public void send(SimpleMailMessage email){
        emailUtil.sendEmail(email);
    }
}
