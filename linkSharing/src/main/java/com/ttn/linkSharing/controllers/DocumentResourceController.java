package com.ttn.linkSharing.controllers;


import com.ttn.linkSharing.CO.DocumentResourceCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.services.DocumentResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DocumentResourceController {
    @Autowired
    DocumentResourceService documentResourceService;


    @RequestMapping(value = "/createDocument", method = RequestMethod.POST)
    public String createDocument(@RequestParam("topic-name") String topic,
                                 @ModelAttribute("documentResourceCO") DocumentResourceCO documentResourceCO,
                                 @RequestParam("documentUpload") MultipartFile file,
                                 HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession.getAttribute("user") != null) {

            UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");

            if (!file.isEmpty()) {

                documentResourceService.saveDocument(documentResourceCO, userDTO, file,topic);
                redirectAttributes.addFlashAttribute("DocumentMsg","Document created Successfully");
            }

        }

        return "redirect:/dashboard";
    }
}
