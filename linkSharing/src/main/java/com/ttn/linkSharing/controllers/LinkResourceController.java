package com.ttn.linkSharing.controllers;

import com.ttn.linkSharing.CO.LinkResourceCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.services.LinkResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LinkResourceController {

    @Autowired
    LinkResourceService linkResourceService;
    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/createLink")
    public String createLink(@ModelAttribute("linkResourceCO") LinkResourceCO linkResourceCO,
                             @RequestParam("link-topic-name") String topic,
                             HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {

        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession.getAttribute("user") != null) {

            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
            linkResourceService.saveLink(userDTO,linkResourceCO,topic);
            logger.debug("LInk REsource URL------------------",linkResourceCO.getLinkURL());
          redirectAttributes.addFlashAttribute("LinkMsg","Link Resource has been created Successfully");
        }
        return "redirect:/dashboard";
    }
}
