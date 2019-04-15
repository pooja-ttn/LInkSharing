package com.ttn.linkSharing.services;

import com.ttn.linkSharing.CO.SubscriptionCO;
import com.ttn.linkSharing.CO.TopicCO;
import com.ttn.linkSharing.CO.UserCO;
import com.ttn.linkSharing.DTO.UserDTO;
import com.ttn.linkSharing.Utils.PassWordUtils;
import com.ttn.linkSharing.convertor.COToEntityViceVersaConvertor;
import com.ttn.linkSharing.convertor.DTOToEntityViceVersaConvertor;
import com.ttn.linkSharing.entities.Subscription;
import com.ttn.linkSharing.entities.Topic;
import com.ttn.linkSharing.entities.User;
import com.ttn.linkSharing.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class EditProfileService {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;
    @Autowired
    SubscriptionService subscriptionService;

    private static final String UPLOAD_FILE = "/home/ttn/Downloads/linkSharing/out/production/resources/static/images/";

/*
    public void saveProfile(UserDTO userDTO, TopicCO topicCO, SubscriptionCO subscriptionCO)
    {
        User user= DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
        Topic topic=COToEntityViceVersaConvertor.COconvertorToEntity(topicCO);
        Subscription subscription=COToEntityViceVersaConvertor.COconvertorToEntity(subscriptionCO);
        topic.se




    }*/

public  void saveProfile(UserCO userCO, UserDTO userDTO, MultipartFile file) throws IOException{
    User user=DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
    user.setFirstName(userCO.getFirstName());
    user.setLastName(userCO.getLastName());

    user.setVerified(true);
    if (file.isEmpty()) {
        user.setPhoto(user.getUserName() + "_" + "User.png");

    }
    user.setUpdatedDate(new Date());
    user.setActive(true);
    try {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_FILE + user.getUserName() + "_" + file.getOriginalFilename());
        Files.write(path, bytes);
        user.setPhoto(user.getUserName() + "_" + file.getOriginalFilename());
    } catch (IOException e) {
        e.printStackTrace();
    }
    userService.save(user);
}

public void savePassword(UserCO userCO,UserDTO userDTO){
    User user= DTOToEntityViceVersaConvertor.DTOToEntity(userDTO);
user.setPassword(PassWordUtils.encrypt(userCO.getPassword()));
userService.save(user);
}
}
